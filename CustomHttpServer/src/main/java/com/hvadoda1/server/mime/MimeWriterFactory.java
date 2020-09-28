package com.hvadoda1.server.mime;

import java.util.HashMap;
import java.util.Map;

public class MimeWriterFactory {
	private static Map<String, IMimeWriter> mimeTypes = new HashMap<String, IMimeWriter>();

	public static IMimeWriter factory(String mimeType) {
		if (!mimeTypes.containsKey(mimeType))
			mimeTypes.put(mimeType, initialize(mimeType));
		return mimeTypes.get(mimeType);

	}

	private static IMimeWriter initialize(String mimeType) {
//		switch (mimeType.split("/")[0]) {
//		case "text":
//			return new TextMimeWriter();
//		case "image":
//			return new ImageMimeWriter();
//		case "video":
//			return new DataMimeWriter();

//		default:
			return new DataMimeWriter();
//			throw new IllegalArgumentException("Unexpected value: " + mimeType);
//		}
	}
}
