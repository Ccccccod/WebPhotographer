

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <link rel="stylesheet" type="text/css" href="css/contact.css">
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp" %>
            <div class="content-wrap">
                <div class="left-area">
                    <div class="large-title">Contact</div>
                    <div class="contact-title">Photographer</div>
                    <div class="contact">Address :   ${info.address}</div>
                    <div class="contact" >City :   ${info.city}</div>                   
                    <div class="contact">Country :   ${info.country}</div>
                    <br>
                    <br>
                    <div class="contact">Tel :   ${info.tel}</div>
                    <br>

                    <div class="contact">Email :   ${info.email}</div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <img src="${info.image}">
                </div>
                <div class="right-area">
                    <%@include file="right.jsp"%>
                </div>
            </div>
            <footer>
                <%@include file="footer.jsp"%>
            </footer>
        </div>  
    </body>
</html>
