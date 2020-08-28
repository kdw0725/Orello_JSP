<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <style>
            body {
                background-color: #222;
            }
            #content {
                width: 500px;
                margin: 0 auto;
            }
            #main {
                width: 550px;
                height: 700px;
                margin-top: 200px;
            }
            .line #top_line {
                border-top: 1px solid #ddd;
                width: 550px;
                position: relative;
                left: 0;
                top: 0px;
                animation: openTopLine 1.8s;
            }

            .line #bottom_line {
                border-top: 1px solid #ddd;
                width: 550px;
                position: relative;
                left: 0;
                top: 560px;
                /* margin-top: 500px; */
                animation: openBottomLine 1.8s;
            }

            @keyframes openTopLine {
                0% {
                    width: 0;
                    transform: translateY(0);
                }
                50% {
                    width: 550px;
                    transform: translateY(250px);
                }
                100% {
                    width: 550px;
                    transform: translateY(0);
                }
            }

            @keyframes openBottomLine {
                0% {
                    width: 0;
                    transform: translateY(0);
                }
                50% {
                    width: 550px;
                    transform: translateY(-250px);
                }
                100% {
                    width: 550px;
                    transform: translateY(0);
                }
            }

            #main #content {
                width: 500px;
                height: 650px;
                animation: slideContent 3.5s;
                /* animation-delay: 2s; */
            }

            @keyframes slideContent {
                0%,
                50% {
                    transform: translateY(70px);
                    opacity: 0;
                }
                100% {
                    transform: translateY(0);
                    opacity: 1;
                }
            }

            #main #content > img {
                margin-left: 150px;
                margin-top: 30px;
            }

            #main #content > h1 {
                margin-left: 205px;
                color: #888;
                text-shadow: 2px 2px 3px #111;
            }
            #main #content > h2 {
                font-size: 14px;
                font-weight: lighter;
                margin-left: 25px;
                color: #666;
                letter-spacing: 1px;
            }

            #main #btn {
                text-align: center;
            }

            #main #btn > button {
                outline: none;
                border-radius: 20px;
                border: 2px solid #888;
                background-color: #333;
                color: #888;
                width: 100px;
                height: 30px;
                position: relative;
                left: 0;
                top: -185px;
                margin-left: 15px;
                margin-top: 20px;
                cursor: pointer;
            }
            #main #btn > button:first-child {
                animation: slideBtn1 5s;
            }
            #main #btn > button:last-child {
                animation: slideBtn2 5s;
                /* animation-delay: 2s; */
            }
            @keyframes slideBtn1 {
                0%,
                40% {
                    transform: translateY(30px);
                    opacity: 0;
                }
                100% {
                    transform: translateY(0px);
                    opacity: 1;
                }
            }
            @keyframes slideBtn2 {
                0%,
                50% {
                    transform: translateY(30px);
                    opacity: 0;
                }
                100% {
                    transform: translateY(0px);
                    opacity: 1;
                }
            }
            
             #main > a {
                color: #444;
                display: block;
                /* margin-bottom: 200px; */
                position: relative;
                top: -130px;
                left: 225px;
                animation: admintag 4s;
                cursor: pointer;
                text-decoration: underline;
            }
            @keyframes admintag {
                0%,
                80% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }

        </style>
    </head>
    <body>
        <div id="content">
            <div id="main">
                <div class="line">
                    <div id="top_line"></div>
                    <div id="bottom_line"></div>
                </div>
                <div id="content">
                    <img src="/orello/images/logo.png" id="logo" />
                    <h1 class="vintage name">
                        Orello
                    </h1>
                    <h2>
                        Orello is tool that help us to manage projects. 
						It can give you chatting, scheduler, board etc.
						sit amet consectetur adipisicing elit. Tempore, accusamus.
						sit amet consectetur adipisicing elit. Tempore, accusamus.
						sit amet consectetur adipisicing elit. Tempore, accusamus.
						sit amet consectetur adipisicing elit. Tempore, accusamus.
						If you use this, you can feel more comfortable.
						Press this button and enjoy our fantastic tool :) 
                    </h2>
                </div>

                <div id="btn">
                    <button onclick="location.href='/orello/member/login.do';">sign in</button>
                    <button onclick="location.href='/orello/member/signin.do';">join in</button>
                </div>
                
                 <a onclick="location.href='#'"><b>admin login</b></a>
            </div>
            
        </div>
    </body>
</html>