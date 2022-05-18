

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header-wrap">
            <div class="h-nav">
                <div class="h-nav-container">
                    <ul class="h-nav-menu">
                        <li><a class="${clickedHome==true?"onClick":""}" href="HomeController" >My front page</a></li>    
                        

                        <c:forEach var="x" items="${galleries}">
                            <li>
                                <a class="${id==x.id?"onClick":""}" href="GalleryController?id=${x.id}">${x.name}</a>
                            </li>
                        </c:forEach>
                        <li><a class="${clickedContact==true?"onClick":""}" href="ContactController">Contact</a></li>         
                    </ul>
                </div>
            </div>
            <div class="banner">
                <div class="banner-container">
                    <div class="banner-logo"></div>
                    <div class="banner-text">
                        <div class="banner-text-large">Photographer</div>
                        <div class="banner-text-medium">Welcome to this website</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
