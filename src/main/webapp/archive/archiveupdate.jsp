<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* 추가된 CSS */
    .dialog-container {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
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
                <button type="button" class="openModalButton">${archive.a_collabomember}</button>
                <div class="dialog-container" id="myModal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <div id="checkboxForm" class="form-container">
                            <p>Select your options:</p>
                            <label><input type="checkbox" name="option1" value="Option 1"> Option 1</label><br>
                            <label><input type="checkbox" name="option2" value="Option 2"> Option 2</label><br>
                            <label><input type="checkbox" name="option3" value="Option 3"> Option 3</label><br>
                            <button type="button" class="submitButton">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="archive-category">
                <div>カテゴリー</div>
                <select name="category">
                    <option value="雑談">雑談</option>
                    <option value="歌枠">歌枠</option>
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
        button.addEventListener("click", function(event) {
            event.preventDefault();
            var modal = this.nextElementSibling;
            modal.style.display = "block";
        });
    });

    closeButtons.forEach(function(button) {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            var modal = this.closest(".dialog-container");
            modal.style.display = "none";
        });
    });

    submitButtons.forEach(function(button) {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            console.log("Submit button clicked");
            var form = this.closest(".dialog-container").querySelector(".form-container");
            var selectedOptions = [];
            var checkboxes = form.querySelectorAll('input[type="checkbox"]:checked');
            checkboxes.forEach(function(checkbox) {
                selectedOptions.push(checkbox.value);
            });
            alert("Selected options: " + selectedOptions.join(", "));
            var parentForm = form.closest("form");
            var hiddenInput = document.createElement("input");
            hiddenInput.type = "hidden";
            hiddenInput.name = "selectedOptions";
            hiddenInput.value = selectedOptions.join(",");
            parentForm.appendChild(hiddenInput);
            parentForm.submit();
        });
    });
});
</script>

</body>
</html>



