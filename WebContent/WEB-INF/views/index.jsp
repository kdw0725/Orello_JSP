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
                        Lorem ipsum dolor sit amet, consectetur adipisicing
                        elit. Reprehenderit culpa dolor id nemo eaque similique
                        praesentium, illo accusantium itaque repellendus? Lorem
                        ipsum, dolor sit amet consectetur adipisicing elit.
                        Voluptas, impedit minima ea culpa quae hic repellat
                        adipisci vero doloribus iste, quia officiis tempora.
                        Saepe neque aspernatur hic distinctio, asperiores
                        mollitia!
                    </h2>
                </div>

                <div id="btn">
                    <button>sign in</button>
                    <button>join in</button>
                </div>
            </div>
        </div>
    </body>
</html>