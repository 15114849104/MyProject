<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>报表页面</title>

		<script type="text/javascript" src="/naire/front/highchartsjs/jquery.min.js"></script>
		<style type="text/css">
            ${demo.css}
		</style>
		<script type="text/javascript">
				$(function () {
				    $('#container').highcharts({
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: '满意度调查报表'
				        },
				        subtitle: {
				            text: '分数报表'
				        },
				        xAxis: {
				            categories: [
								<c:forEach items="${list1}" var="l">
								'${l.content}',
								</c:forEach>
				            ],
				            crosshair: true
				        },
				        yAxis: {
				            min: 0,
				            title: {
				                text: '分数 (分)'
				            }
				        },
				        tooltip: {
				            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				                '<td style="padding:0"><b>{point.y:.1f} 分</b></td></tr>',
				            footerFormat: '</table>',
				            shared: true,
				            useHTML: true
				        },
				        plotOptions: {
				            column: {
				                pointPadding: 0.2,
				                borderWidth: 0
				            }
				        },
				        series: [
					   <c:forEach items="${map}" var="m">
					      {
						   name:'${m.key}',
						   data:${m.value}
						   },
						</c:forEach>
				        ]
				    });
				});
		</script>
	</head>
	<body>
		<script src="/naire/front/highchartsjs/highcharts.js"></script>
		<script src="/naire/front/highchartsjs/exporting.js"></script>
		
		<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	</body>
</html>
