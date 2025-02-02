package com.hvadoda1.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerFactory {

	private static boolean constructorMatches(Constructor<?> constr, Object... args) {
		if (constr.getParameterCount() != args.length)
			return false;
		Parameter[] params = constr.getParameters();
		for (int i = 0; i < args.length; i++) {
			if (!params[i].getType().isAssignableFrom(args[i].getClass())
					&& !isWrapper(params[i].getType().getSimpleName(), args[i].getClass().getSimpleName()))
				return false;
		}
		return true;
	}

	private static boolean isWrapper(String type, String wrapper) {
		switch (type) {
		case "int":
			return wrapper.equals("Integer");
		case "char":
			return wrapper.equals("CHaracter");
		case "double":
			return wrapper.equals("Double");
		case "long":
			return wrapper.equals("Long");
		}

		return false;
	}

	public static final IServer<?> factory(ServerType server, Object... args) {
		Class<? extends IServer<?>> clazz = server.getServerClass();
		try {
			Constructor<? extends IServer<?>> constr = Stream.of(clazz.getConstructors())
					.map(con -> (Constructor<? extends IServer<?>>) con)
					.filter(con -> con.getParameterCount() == args.length && constructorMatches(con, args)).findFirst()
					.orElse(null);

			// Initialize the constructor with parameters 'args'
			if (constr == null)
				throw new RuntimeException(
						"Constructor with provided parameters not found, could not instantiate " + server);
			new Config();
			return constr.newInstance(args);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Failed to instantiate Server of Type [" + server.name()
					+ "] : Invalid declaration of State constructor or Incorrect number/types of arguments provided (Got "
					+ args.length + " arguments of types ["
					+ Stream.of(args).map(arg -> arg.getClass().getName()).collect(Collectors.joining(", "))
					+ "] as constructor parameters)", e);
		} catch (SecurityException | IllegalAccessException e) {
			throw new RuntimeException("Failed to instantiate Server of Type [" + server.name()
					+ "] : Constructor is not accessible, make sure the constructor is public)", e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Failed to instantiate Server of Type [" + server.name() + "]", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"Failed to instantiate Server of Type [" + server.name() + "] : Invalid arguments", e);
		}
	}

}
