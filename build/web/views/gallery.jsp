
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <link rel="stylesheet" type="text/css" href="css/gallery.css">
    </head>
    <body>

        <div class="wrapper">  

            <jsp:include page="header.jsp"/>

            <div class="content-wrap">
                <div class="left-area">
                    <div class="name">${gal.name}</div>
                    <div class="slide">

                        <h2>${message}</h2>


                        <c:forEach var="x" items="${imgs}">
                            <img class="gallery-big-image" src="${x.image}" >
                        </c:forEach>
                    </div>


                    <div class="gallery-list-image">
                        <c:forEach var="x" items="${imgs}" varStatus="loop" >
                            
                            <div class="gallery-image" >

                                <img id="choiceImage" 
                                     src="${x.image}" 
                                     onclick="showImageChoose(${loop.index})">

                            </div>

                        </c:forEach>
                    </div>


                    <div class="paging">
                        <c:if test="${maxPage<1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage>1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==pageIndex?"active":""}" href="GalleryController?txtPage=${i}&id=${id}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>

                </div>

                <div class="right-area">
                    <jsp:include page="right.jsp"/>
                </div>

            </div>

            <jsp:include page="footer.jsp"/>
        </div>
    </body>

    <script src="js/gallery.js">
    </script>

</html>