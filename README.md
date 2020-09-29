# Multi-Threaded Custom HTTP Server
#### - Harshit Vadodaria
---
An implementation of a Simple Multi-threaded HTTP Server, using TCP Sockets.

This server supports basic HTTP GET requests, requesting a file of any Mime Type (text, image, video, application etc.).

It demonstrates how a simple Server can process HTTP requests, by processing the request headers expected to be of format specified by `RFC 822`, and send out the response headers using the same generic format, followed by the requested resource.

This server also supports multiple threads, and thus multiple GET requests can be processed at the same time. The number of threads can be specified by the optional command line argument "threads" (Refer **Command Line Args** below).

The server needs a directory `www/` in the execution directory, and this directory is treated as the root HTML directory to be served.

### Compilation and execution steps (Only on a Linux-based OS - Ubuntu, MacOS etc.):
> Needs 'Java' 8+ installed
1. To ***compile*** the server, execute the script *server.sh*: `./server.sh`

	(If you get a permissions error, you can fix it by adding execute permissions for the current user:
	
	`chmod +700 server.sh`
	
	)

	This will create an executable *server.jar*
2.  To ***run*** this executable:
	
    `java -jar server.jar`

    This will print to the console the hostname of the server, and the port used for serving HTTP content (example: abc.xyz:80)
	  The server does not need any config file or command line arguments, however, you can *optionally* provide some command line arguments as key-value pairs (Refer **Command Line Args** below).

### Logging
The server logs details about every request, both to the console (minimal, summarized log entry) and to a log file (detailed log entry) in the `logs/` directory. For every request, the server logs details like requested resource, client IP address, client port number and number of times this resource was accessed, in a pipe-separated format.
Additional debugging details can be logged by setting the log level using the optional command line argument "level".

Below logs level are supported:
- 0 : None
- 1 : Error
- 2 : Warn
- 3 : Info (Default)
- 4 : Config
- 5 : Debug Low
- 6 : Debug Medium
- 7 : Debug High

### Command Line Arguments
The server does not need any command line arguments to run.
Optionally, however, you can pass any of the below available command line arguments (in any order), using the syntax `key=value`:
 - threads
 
	  Max number of threads the server should run on.
    
	  Default: -1
	  
    Values:
	  - <= 0
		  No limit on the number of threads (Inifinite threads)
	  - \>= 1
- port
	
  Port number
	
  Default: 80, or any available port between 8080 and 65535

- name
	
  Name for the Server.
	
  Default: CustomHttpServer
- httpdelay
	
  Delay (in seconds), for every response from the server, to simulate real world network latency.
	
  Example, `httpdelay=10` will make the server wait 10 seconds before every response.
	
  Useful in debugging the multi-threaded model.
- level
	
  Log level (numeric, refer **Logging** above)
	
  Default: 3
