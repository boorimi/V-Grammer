<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${archives}" var="archive">
    <form action="ArchiveUpdateC" method="post">
        <div class="archive-contents-update">
            <p style="margin-top: 0px">
                <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" >
            </p>
            <input type="hidden" name="a_pk" value="${archive.a_pk}">
            <div class="archive-membername">${archive.m_name}</div>
            
            <div class="archive-collabo">
                <div>コラボ</div>
               <%--  <input type="text" name="collabo" value="${archive.a_collabo}"> --%>
                <select name="collabo">
    				<option value="yes">Yes</option>
   					 <option value="no">No</option>
  				</select>
            </div>
            
            <div class="archive-collabomember">
                <div>コラボメンバー</div>
                <input type="text" name="collabomember" value="${archive.a_collabomember}">
            </div>
            <div class="archive-category">
                <div>カテゴリー</div>
                <select name="category">
    				<option value="雑談">雑談</option>
   					 <option value="歌枠">歌枠</option>
   					 <option value="企画">企画</option>
   					 <option value="ASMR">ASMR</option>
   					 <option value="歌みた">歌みた</option>
   					 <option value="shorts">shorts</option>
   					 <option value="切り抜き">切り抜き</option>
   					 <option value="オリジナル曲">オリジナル曲</option>
   					 <option value="他">他</option>
   					 
   					 
   					 
  				</select>
            </div>
            <div class="archive-date">${archive.a_date}</div>
            <div class="archive-time">${archive.a_time}</div>
            <div class="archive-title">Title: ${archive.a_title}</div>
            <div class="archive-thumbnail">
                <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail">
            </div>
            <button type="submit">수정</button>
        </div>
    </form>
</c:forEach>

</body>
</html>


