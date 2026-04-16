const CORRECT_PASSWORD = "1229"; 
let currentInput = "";

updatePasswordDisplay();

function nextScreen(num, useLoader = true, loaderDuration = 3000) {
    const activeScreen = document.querySelector('.screen.active');
    const loadingScreen = document.getElementById('screen-loading');
    const targetScreen = document.getElementById(`screen-${num}`);

    // Stop any videos that might be playing when changing screens
    document.querySelectorAll('video').forEach(vid => vid.pause());

    activeScreen.classList.remove('active');

    if (useLoader) {
        setTimeout(() => {
            loadingScreen.classList.add('active'); 
            
            setTimeout(() => {
                loadingScreen.classList.remove('active'); 
                
                setTimeout(() => {
                    targetScreen.classList.add('active'); 
                }, 500); 

            }, loaderDuration); 

        }, 500); 
    } else {
        setTimeout(() => {
            targetScreen.classList.add('active');
        }, 500);
    }
}

function addNumber(num) {
    if (currentInput.length < 4) {
        currentInput += num;
        updatePasswordDisplay();
        document.getElementById('error-msg').style.opacity = '0';
    }
}

function clearPassword() {
    currentInput = "";
    updatePasswordDisplay();
    document.getElementById('error-msg').style.opacity = '0';
}

function updatePasswordDisplay() {
    let displayStr = "";
    for(let i=0; i<4; i++) {
        if(i < currentInput.length) {
            displayStr += "★ "; 
        } else {
            displayStr += "☆ ";
        }
    }
    document.getElementById('password-display').innerText = displayStr.trim();
}

function checkPassword() {
    if (currentInput === CORRECT_PASSWORD) {
        nextScreen(2, true, 3000);
    } else {
        document.getElementById('error-msg').style.opacity = '1';
        currentInput = "";
        setTimeout(updatePasswordDisplay, 400); 
    }
}

function openGift(giftNumber) {
    const titleElement = document.getElementById('gift-title');
    const contentElement = document.getElementById('gift-content');

    if (giftNumber === 1) {
        titleElement.innerText = "You & Me";
        contentElement.innerHTML = '<img src="IMG-20260324-WA0047.jpg" class="content-image" alt="Us"><p>Distance means nothing when someone means everything.</p>'; 
    
    } else if (giftNumber === 2) {
        titleElement.innerText = ""; 
        contentElement.innerHTML = `
            <div class="majestic-bouquet-card">
                <img src="Thème Marron.jpg" alt="Majestic Bouquet">
            </div>
            <p style="font-size: 16px; font-style: italic;">I wish I could hand these to you right now.</p>
        `;
    
    } else if (giftNumber === 3) {
        titleElement.innerText = ""; 
        contentElement.innerHTML = `
            <div class="floating-letter-wrapper">
                <div class="polaroid">
                    <img src="IMG-20251231-WA0006.jpg" alt="Memory 1">
                </div>
                
                <div class="polaroid polaroid-2">
                    <img src="https://images.unsplash.com/photo-1606253457588-4660ab933614?q=80&w=300&auto=format&fit=crop" alt="Memory 2">
                </div>

                <div class="love-letter">
                    My beautiful girl,<br><br>
                    Even with all this distance between us, you are the closest thing to my heart. I built this just to see you smile today, because your happiness is my favorite thing in the world.<br><br>
                    I'm counting down the seconds until I can finally hold you. I love you so much.<br><br>
                    Forever yours. ❤️
                </div>
            </div>
        `;
        
    } else if (giftNumber === 4) {
        titleElement.innerText = "Just For You"; 
        
        contentElement.innerHTML = `
            <video id="special-video" class="content-video" controls playsinline>
                <source src="lv_7464580203633823029_20260416122921.mp4" type="video/mp4">
                Your browser does not support the video tag.
            </video>
            <p>A little something I made.</p>
        `;

        setTimeout(() => {
            let myVideo = document.getElementById("special-video");
            if (myVideo) {
                myVideo.play().catch(error => console.log("User must interact first to allow audio"));
            }
        }, 4000);
    }

    nextScreen(4, true, 1500);
}
