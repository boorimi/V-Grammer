<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>YouTube Live Slider</title>
<style>
    #slider-container {
        width: 600px;
        overflow: hidden;
        position: relative;
    }

    #slider-content {
        display: flex;
        transition: transform 0.5s ease;
    }

    .slide {
        width: 600px;
        flex-shrink: 0;
    }

    iframe {
        width: 100%;
        height: 350px;
    }

    .btn {
        cursor: pointer;
        margin: 10px;
    }
</style>
</head>
<body>

<div id="slider-container">
    <div id="slider-content">
        <!-- Slides will be added dynamically -->
    </div>
</div>

<button class="btn" onclick="prevSlide()">Previous</button>
<button class="btn" onclick="nextSlide()">Next</button>

<script>
    const slider = document.getElementById('slider-content');
    const videos = [
        "ramD-7CMg-8",
        "C75OWJWMVFw",
        "uGK78ywXY4Y"
    ];
    let currentVideo = 0;

    function loadSlides() {
        videos.forEach((video) => {
            const slide = document.createElement('div');
            slide.className = 'slide';

            const iframe = document.createElement('iframe');
            iframe.src = `https://www.youtube.com/embed/${video}`;
            iframe.setAttribute('frameborder', '0');
            iframe.setAttribute('allow', 'accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture');
            iframe.allowFullscreen = true;

            slide.appendChild(iframe);
            slider.appendChild(slide);
        });
    }

    function showVideo(videoIndex) {
        const offset = -videoIndex * 600; // Adjust the value 600 to the width of your slide
        slider.style.transform = `translateX(${offset}px)`;
    }

    function prevSlide() {
        currentVideo = (currentVideo - 1 + videos.length) % videos.length;
        showVideo(currentVideo);
    }

    function nextSlide() {
        currentVideo = (currentVideo + 1) % videos.length;
        showVideo(currentVideo);
    }

    // Load slides and show the first video initially
    loadSlides();
    showVideo(currentVideo);
</script>

</body>
</html>








