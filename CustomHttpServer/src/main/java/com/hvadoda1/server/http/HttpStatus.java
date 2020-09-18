package com.hvadoda1.server.http;

public enum HttpStatus {
	/** Continue */
	HTTP_100(100, "Continue"),
	/** Switching Protocol */
	HTTP_101(101, "Switching Protocol"),
	/** Processing */
	HTTP_102(102, "Processing"),
	/** Early Hints */
	HTTP_103(103, "Early Hints"),
	/** OK */
	HTTP_200(200, "OK"),
	/** Created */
	HTTP_201(201, "Created"),
	/** Accepted */
	HTTP_202(202, "Accepted"),
	/** Non-Authoritative Information */
	HTTP_203(203, "Non-Authoritative Information"),
	/** No Content */
	HTTP_204(204, "No Content"),
	/** Reset Content */
	HTTP_205(205, "Reset Content"),
	/** Partial Content */
	HTTP_206(206, "Partial Content"),
	/** Multi-Status */
	HTTP_207(207, "Multi-Status"),
	/** Already Reported */
	HTTP_208(208, "Already Reported"),
	/** IM Used */
	HTTP_226(226, "IM Used"),
	/** Multiple Choice */
	HTTP_300(300, "Multiple Choice"),
	/** Moved Permanently */
	HTTP_301(301, "Moved Permanently"),
	/** Found */
	HTTP_302(302, "Found"),
	/** See Other */
	HTTP_303(303, "See Other"),
	/** Not Modified */
	HTTP_304(304, "Not Modified"),
	/** Temporary Redirect */
	HTTP_307(307, "Temporary Redirect"),
	/** Permanent Redirect */
	HTTP_308(308, "Permanent Redirect"),
	/** Bad Request */
	HTTP_400(400, "Bad Request"),
	/** Unauthorized */
	HTTP_401(401, "Unauthorized"),
	/** Payment Required */
	HTTP_402(402, "Payment Required"),
	/** Forbidden */
	HTTP_403(403, "Forbidden"),
	/** Not Found */
	HTTP_404(404, "Not Found"),
	/** Method Not Allowed */
	HTTP_405(405, "Method Not Allowed"),
	/** Not Acceptable */
	HTTP_406(406, "Not Acceptable"),
	/** Proxy Authentication Required */
	HTTP_407(407, "Proxy Authentication Required"),
	/** Request Timeout */
	HTTP_408(408, "Request Timeout"),
	/** Conflict */
	HTTP_409(409, "Conflict"),
	/** Gone */
	HTTP_410(410, "Gone"),
	/** Length Required */
	HTTP_411(411, "Length Required"),
	/** Precondition Failed */
	HTTP_412(412, "Precondition Failed"),
	/** Payload Too Large */
	HTTP_413(413, "Payload Too Large"),
	/** URI Too Long */
	HTTP_414(414, "URI Too Long"),
	/** Unsupported Media Type */
	HTTP_415(415, "Unsupported Media Type"),
	/** Range Not Satisfiable */
	HTTP_416(416, "Range Not Satisfiable"),
	/** Expectation Failed */
	HTTP_417(417, "Expectation Failed"),
	/** I'm a teapot */
	HTTP_418(418, "I'm a teapot"),
	/** Misdirected Request */
	HTTP_421(421, "Misdirected Request"),
	/** Unprocessable Entity */
	HTTP_422(422, "Unprocessable Entity"),
	/** Locked */
	HTTP_423(423, "Locked"),
	/** Failed Dependency */
	HTTP_424(424, "Failed Dependency"),
	/** Too Early */
	HTTP_425(425, "Too Early"),
	/** Upgrade Required */
	HTTP_426(426, "Upgrade Required"),
	/** Precondition Required */
	HTTP_428(428, "Precondition Required"),
	/** Too Many Requests */
	HTTP_429(429, "Too Many Requests"),
	/** Request Header Fields Too Large */
	HTTP_431(431, "Request Header Fields Too Large"),
	/** Unavailable For Legal Reasons */
	HTTP_451(451, "Unavailable For Legal Reasons"),
	/** Internal Server Error */
	HTTP_500(500, "Internal Server Error"),
	/** Not Implemented */
	HTTP_501(501, "Not Implemented"),
	/** Bad Gateway */
	HTTP_502(502, "Bad Gateway"),
	/** Service Unavailable */
	HTTP_503(503, "Service Unavailable"),
	/** Gateway Timeout */
	HTTP_504(504, "Gateway Timeout"),
	/** HTTP Version Not Supported */
	HTTP_505(505, "HTTP Version Not Supported"),
	/** Variant Also Negotiates */
	HTTP_506(506, "Variant Also Negotiates"),
	/** Insufficient Storage */
	HTTP_507(507, "Insufficient Storage"),
	/** Loop Detected */
	HTTP_508(508, "Loop Detected"),
	/** Not Extended */
	HTTP_510(510, "Not Extended"),
	/** Network Authentication Required */
	HTTP_511(511, "Network Authentication Required"),;

	public final int statusNumber;
	public final String description;

	private HttpStatus(int statusNumber, String description) {
		this.statusNumber = statusNumber;
		this.description = description;
	}

	@Override
	public String toString() {
		return statusNumber + " " + description;
	}
	
	
}
