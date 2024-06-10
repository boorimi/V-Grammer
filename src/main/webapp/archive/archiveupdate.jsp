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
                <select name="collabo">
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select>
            </div>
            
            <div class="archive-collabomember">
                <div>コラボメンバー</div>
                <div class="modal-button-wrapper">
                    <button class="openModalButton" type="button">${archive.a_collabomember}</button>
                    <div class="dialog-container">
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <form class="form-container" id="checkboxForm">
                                <p>Select your options:</p>
                                <label><input type="checkbox" name="option1" value="Option 1"> Option 1</label>
                                <label><input type="checkbox" name="option2" value="Option 2"> Option 2</label>
                                <label><input type="checkbox" name="option3" value="Option 3"> Option 3</label>
                                <label><input type="checkbox" name="option4" value="Option 4"> Option 4</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label>
                                <!-- Add more checkboxes if needed -->
                                <button type="button" class="submitButton">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="archive-category">
                <div>カテゴリー</div>
                <select name="category">
                    <option value="雑談">雑談</option>
                    <option value="歌枠">歌枠</option>
                    <option value="企画">企画</option>
                    <!-- Add more options if needed -->
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

<script>
document.addEventListener("DOMContentLoaded", function() {
    var openModalButtons = document.querySelectorAll(".openModalButton");
    var closeButtons = document.querySelectorAll(".dialog-container .close");
    var submitButtons = document.querySelectorAll(".submitButton");

    openModalButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var modal = this.closest(".archive-collabomember").querySelector(".dialog-container");
            modal.classList.add("open");
        });
    });

    closeButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var modal = this.closest(".dialog-container");
            modal.classList.remove("open");
        });
    });

    submitButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var form = this.closest(".form-container");
            var selectedOptions = [];
            var checkboxes = form.querySelectorAll('input[type="checkbox"]:checked');
            checkboxes.forEach(function(checkbox) {
                selectedOptions.push(checkbox.value);
            });
            alert("Selected options: " + selectedOptions.join(", "));
            var modal = this.closest(".dialog-container");
            modal.classList.remove("open");
        });
    });
});
</script>
</body>
</html>