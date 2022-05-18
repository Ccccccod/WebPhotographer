

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="/My_Photographer/css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="content-wrap">
            <div class="left-area">
                <h1>Something is wrong !!!</h1>
            </div>

            <div class="right-area">
                <%@include file="right.jsp"%>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
