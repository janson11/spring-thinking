<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<html>
<head>
    <title></title>
</head>
<body>
<!--  page ->request ->session ->application(ServletContext)  -->
\${userObject.name} : ${userObject.name}
\${applicationScope['scopedTarget.user'].name} : ${applicationScope['scopedTarget.user'].name}
</body>
</html>
