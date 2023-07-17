<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>


        <script src="http://code.jquery.com/jquery-latest.js"></script>

        <script>


            $(function () {

                $("#checkJson").click(function () {

                    var newArticle = { articleNO: 102, writer: "홍2", title: "홍길동전2", content: "아버지를아버지라" };

                    $.ajax({

                        
                        type: "put",
                        url: "${contextPath}/boards",
                        contentType: "application/json",
                        data: JSON.stringify(newArticle), //JavaScript 값이나 객체를 JSON 문자열로 변환합니다
                        /* data: member, */
                        success: function (data, textStatus) {
                        },
                        error: function (data, textStatus) {
                            alert("에러가 발생했습니다.");
                        },
                        complete: function (data, textStatus) {
                        }

                    });



                });

            });






        </script>


    </head>

    <body>
        <!-- dd -->

        <div>

            <input type="button" value="클라이언트에서 서버로 글 추가하기" id="checkJson">
        </div>
    </body>

    </html>