/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import org.springframework.http.HttpStatus;
/**
 *
 * @author javaugi
 */
public class HttpStatusConfig {
    private HttpStatus status;
}
/*
Enum Constant Summary
Enum Constants
Enum Constant
Description
ACCEPTED
202 Accepted.
ALREADY_REPORTED
208 Already Reported.
BAD_GATEWAY
502 Bad Gateway.
BAD_REQUEST
400 Bad Request.
BANDWIDTH_LIMIT_EXCEEDED
509 Bandwidth Limit Exceeded
CHECKPOINT
Deprecated.
in favor of EARLY_HINTS which will be returned from HttpStatus.valueOf(103)
CONFLICT
409 Conflict.
CONTINUE
100 Continue.
CREATED
201 Created.
DESTINATION_LOCKED
Deprecated.
See WebDAV Draft Changes
EARLY_HINTS
103 Early Hints.
EXPECTATION_FAILED
417 Expectation Failed.
FAILED_DEPENDENCY
424 Failed Dependency.
FORBIDDEN
403 Forbidden.
FOUND
302 Found.
GATEWAY_TIMEOUT
504 Gateway Timeout.
GONE
410 Gone.
HTTP_VERSION_NOT_SUPPORTED
505 HTTP Version Not Supported.
I_AM_A_TEAPOT
418 I'm a teapot.
IM_USED
226 IM Used.
INSUFFICIENT_SPACE_ON_RESOURCE
Deprecated.
See WebDAV Draft Changes
INSUFFICIENT_STORAGE
507 Insufficient Storage
INTERNAL_SERVER_ERROR
500 Internal Server Error.
LENGTH_REQUIRED
411 Length Required.
LOCKED
423 Locked.
LOOP_DETECTED
508 Loop Detected
METHOD_FAILURE
Deprecated.
See WebDAV Draft Changes
METHOD_NOT_ALLOWED
405 Method Not Allowed.
MOVED_PERMANENTLY
301 Moved Permanently.
MOVED_TEMPORARILY
Deprecated.
in favor of FOUND which will be returned from HttpStatus.valueOf(302)
MULTI_STATUS
207 Multi-Status.
MULTIPLE_CHOICES
300 Multiple Choices.
NETWORK_AUTHENTICATION_REQUIRED
511 Network Authentication Required.
NO_CONTENT
204 No Content.
NON_AUTHORITATIVE_INFORMATION
203 Non-Authoritative Information.
NOT_ACCEPTABLE
406 Not Acceptable.
NOT_EXTENDED
510 Not Extended
NOT_FOUND
404 Not Found.
NOT_IMPLEMENTED
501 Not Implemented.
NOT_MODIFIED
304 Not Modified.
OK
200 OK.
PARTIAL_CONTENT
206 Partial Content.
PAYLOAD_TOO_LARGE
413 Payload Too Large.
PAYMENT_REQUIRED
402 Payment Required.
PERMANENT_REDIRECT
308 Permanent Redirect.
PRECONDITION_FAILED
412 Precondition failed.
PRECONDITION_REQUIRED
428 Precondition Required.
PROCESSING
102 Processing.
PROXY_AUTHENTICATION_REQUIRED
407 Proxy Authentication Required.
REQUEST_ENTITY_TOO_LARGE
Deprecated.
in favor of PAYLOAD_TOO_LARGE which will be returned from HttpStatus.valueOf(413)
REQUEST_HEADER_FIELDS_TOO_LARGE
431 Request Header Fields Too Large.
REQUEST_TIMEOUT
408 Request Timeout.
REQUEST_URI_TOO_LONG
Deprecated.
in favor of URI_TOO_LONG which will be returned from HttpStatus.valueOf(414)
REQUESTED_RANGE_NOT_SATISFIABLE
416 Requested Range Not Satisfiable.
RESET_CONTENT
205 Reset Content.
SEE_OTHER
303 See Other.
SERVICE_UNAVAILABLE
503 Service Unavailable.
SWITCHING_PROTOCOLS
101 Switching Protocols.
TEMPORARY_REDIRECT
307 Temporary Redirect.
TOO_EARLY
425 Too Early.
TOO_MANY_REQUESTS
429 Too Many Requests.
UNAUTHORIZED
401 Unauthorized.
UNAVAILABLE_FOR_LEGAL_REASONS
451 Unavailable For Legal Reasons.
UNPROCESSABLE_ENTITY
422 Unprocessable Entity.
UNSUPPORTED_MEDIA_TYPE
415 Unsupported Media Type.
UPGRADE_REQUIRED
426 Upgrade Required.
URI_TOO_LONG
414 URI Too Long.
*/