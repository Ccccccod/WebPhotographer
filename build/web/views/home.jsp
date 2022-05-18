
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <link rel="stylesheet" type="text/css" href="css/home.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="content-wrap">
            <div class="left-area">
                <!--preIntro-->
                <div class="intro">
                    <c:if test="${intro eq null}">
                        <h2>${errIntro}</h2>
                    </c:if>
                    <div class="intro-image"><img src="${intro.getImage()}"></div>
                    <div class="intro-description">${intro.getEntry()}</div>
                </div>

                <!--End preIntro-->

                <!--show gallarys-->
                <c:if test="${empty galleries}">
                    <p class="error-text">${mess}</p>
                </c:if>
                <div class="gallery-collection">
                    <c:forEach var="x" items="${galleries}">
                        <div class="gallery-collection-item">
                            <div class="gallery-cover"><img src="${x.image}"></div>
                            <div class="gallery-title"><a href="GalleryController?id=${x.id}">View ${x.name}</a></div>
                            <div class="description">${x.description}</div>
                        </div>
                    </c:forEach>
                </div>
                <!--End show gallarys-->

                <!--Paging-->
                <div class="paging">

                    <c:if test="${maxPage<1}">
                        <h3>Not Found !!</h3>
                    </c:if>

                    <c:if test="${maxPage>1}">
                        <c:forEach begin="1" end="${maxPage}" var="i">
                            <a class="${i==pageIndex?"active":""}" href="HomeController?page=${i}">${i}</a>
                        </c:forEach>
                    </c:if>
                    <div class="about-me">
                        <div class="large-title">${about.title}</div>
                        <div class="description">${about.description}</div>
                    </div>



                </div>
                <!--End paging-->

                <!--Intro about me-->
                <c:if test="${intro eq null}">
                    <h2>${errIntro}</h2>
                </c:if>
                <div class="about-me">
                    <div class="large-title">${messIntro}</div>

                    <div class="large-title">About Me</div>
                    <div class="description">${intro.getAboutme()}</div>
                </div>
                <!--End Intro about me-->
            </div>

            <div class="right-area">
                <%@include file="right.jsp"%>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
