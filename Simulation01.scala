package acme

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Simulation01 extends Simulation {

	val serverName = "http://acme.s01.surgery.sbit.io"
	val waitTime = 1

	val httpProtocol = http
		.baseURL(serverName)
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("en-US,en;q=0.8")
		.contentTypeHeader("application/x-www-form-urlencoded")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36")

	val headers_1 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Origin" -> serverName)

	val headers_3 = Map(
		"Accept" -> "text/plain, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_5 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryAgBoOLOSw2upMN96",
		"Origin" -> serverName)

	val headers_7 = Map("Accept" -> "image/webp,*/*;q=0.8")

    val uri1 = serverName

	val scn = scenario("Simulation01")
		.exec(http("request_0")
			.get("/"))
		.pause(waitTime)
		.exec(http("request_1")
			.post("/node?destination=node")
			.headers(headers_1)
			.formParam("name", "admin")
			.formParam("pass", "acme")
			.formParam("form_build_id", "form-dWA0xOwJqgkgSuYxTkEZuhPxu8HRcs4JSci07azqMZk")
			.formParam("form_id", "user_login_block")
			.formParam("op", "Log in"))
		.pause(waitTime)
		.exec(http("request_2")
			.get("/node/add")
			.resources(http("request_3")
			.get(uri1 + "/js/admin_menu/cache/e6f55867ebbd479a8f1d4e4611c474fa")
			.headers(headers_3)))
		.pause(waitTime)
		.exec(http("request_4")
			.get("/node/add/article"))
		.pause(waitTime)
		.exec(http("request_5")
			.post("/node/add/article")
			.headers(headers_5)
			.body(RawFileBody("Simulation01_0005_request.txt")))
		.pause(waitTime)
		.exec(http("request_6")
			.post("/comment/reply/2")
			.headers(headers_1)
			.formParam("subject", "")
			.formParam("comment_body[und][0][value]", "qqq")
			.formParam("comment_body[und][0][format]", "filtered_html")
			.formParam("form_build_id", "form-_79KNyJ-8x06H6N92cVgbO9vUfVsbCS00wu1R9xebj0")
			.formParam("form_token", "9YGmPru06gD-wnBwgHV7LHMGZHbcE11f9cdNWmwmRWI")
			.formParam("form_id", "comment_node_article_form")
			.formParam("op", "Save")
			.resources(http("request_7")
			.get(uri1 + "/themes/bartik/images/comment-arrow.gif")
			.headers(headers_7)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
