const memberList = document.querySelectorAll('.member-memberList');
const memberImgs = document.querySelectorAll('.member-backgroundImg');
memberImgs[0].classList.add('show');
let idx = 0;
memberList.forEach((member) => {
	member.addEventListener("click", (e) => {

		memberImgs.forEach((img) => {
			img.classList.remove('show');
		});

		idx = member.getAttribute('id');
		console.log(memberImgs[idx-1])
		memberImgs[idx-1].classList.add('show');

	});
});
