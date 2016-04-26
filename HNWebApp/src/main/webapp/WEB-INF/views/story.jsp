<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>HNMirror</title>

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script> -->
<script  type="text/javascript">
	function timeConverter(UNIX_timestamp) {
		var date = new Date(UNIX_timestamp * 1000);
		var diff = new Date() - date;

		if (diff < 1000) {
		return 'just now';
		}
		else if (diff < 1000*60) {
		return Math.round(diff/1000) + ' seconds ago';
		}
		else if (diff < 1000*60*60) {
		return Math.round(diff/1000/60) + ' minutes ago';
		}
		else {
		var dd = date.getDate();
		if (dd < 10) dd = '0' + dd;

		var mm = date.getMonth() + 1;
		if (mm < 10) mm = '0' + mm;

		var yy = date.getFullYear() % 100;
		if (yy < 10) yy = '0' + yy;

		var hh = date.getHours(); 
		if (hh < 10) hh = '0' + hh;

		var ms = date.getMinutes();
		if (ms < 10) ms = '0' + ms;

		return mm + '/' + dd + '/' + yy + ' ' + hh + ':' + ms;
		}
		}
</script>

</head>
<body><center>

	<table id="hnmain" border="0" cellpadding="0" cellspacing="0"
		width="85%" bgcolor="#f6f6ef">
		<tr>
			<td bgcolor="#ff6600"><table border="0" cellpadding="0"
					cellspacing="0" width="100%" style="padding: 2px">
					<tr>
						<td style="width: 18px; padding-right: 4px"><a
							href="index"> <img src="images/r18.gif" width="18" height="18"
								style="border: 1px #ffffff solid;">
						</a></td>

						<td style="line-height: 12pt; height: 10px;"><span
							class="pagetop" ><b><a
									style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
									href="index">HNMirror</a></b> <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">new</a> | <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">comments</a> | <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">show</a> | <span class="topsel"><a
									style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
									href="javascript:void(0)">ask</a></span> | <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">jobs</a> | <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">submit</a></span></td>
						<td style="text-align: right; padding-right: 4px;"><span
							class="pagetop"> <a
								style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
								href="javascript:void(0)">login</a>
						</span></td>
					</tr>
				</table></td>
		</tr>
		<tr style="height: 10px">
		</tr>
		<tr>

			<td><form:form method="GET" action="/hnmirror/search">
					<center>
						<table style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;">
							<tr>
								<td> <input type="text" size="40" name="search" ${search_res} ></td>
								<td> <input type="submit" value="Search"> by
									 <input type="checkbox" ${checkboxauthor}  name="searchbyauthor" value="author"> author 
									 <input type="checkbox" ${checkboxtitle} name="searchbytitle" value="title"> title 
									 <input type="checkbox" ${checkboxurl} name="searchbyurl" value="url"> url
									 <input type="checkbox" ${checkboxtext} name="searchbytext" value="text"> text
								</td>
							</tr>

						</table>
					</center>
				</form:form>


				<table border="0" cellpadding="0" cellspacing="0">
					<th><c:if test="${!empty stories}">
							<table class="data">
								<c:forEach items="${stories}" var="story">
									<tr class='athing'>
										<td align="right" valign="top" class="title"><span
											class="rank" style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;">${story.id}</span></td>
										<td><center></center></td>
										<td class="title"><span class="deadmark"></span> <a
											style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
											href=${story.url}>${story.title}</a></td>
									</tr>

									<tr>

										<td colspan="2"></td>
										<td
											style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 7pt; color: #828282;"
											class="subtext"><span class="score">${story.score}
												points</span> by ${story.author} <script type="text/javascript"> 
															document.write(timeConverter(${story.time})); 
														</script> 
									</td>

									</tr>

									<tr class="spacer" style="height: 5px"></tr>
								</c:forEach>
							</table>
						</c:if></th>
					<tr class="spacer" style="height: 5px"></tr>
					<tr class="morespace" style="height: 10px"></tr>
					<tr>
						<td class="title" >
							<a style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
							href="/hnmirror/${page}" rel="nofollow">More</a>
					    </td>
					   
					</tr>
				</table></td>
		</tr>

		<tr>
			<td>
				<table width="100%" cellspacing="0" cellpadding="1">
					<tr>
						<td bgcolor="#ff6600"></td>
					</tr>
				</table> <br>
				<center>
					<a
						style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
						href="index">2015 BRS</a>
				</center> <br>
				<center>
					<span class="yclinks">
						<a style="text-decoration: none; font-family: Verdana, Geneva, sans-serif; font-size: 10pt; color: #000000;"
						href="mailto:bodryx@gmail.com">Contact</a></span><br> <br>

				</center>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
