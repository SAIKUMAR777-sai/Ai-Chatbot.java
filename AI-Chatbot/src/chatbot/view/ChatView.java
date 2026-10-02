/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatbot.view;


/** VIEW: the chat page (HTML) shown in the browser. */
public class ChatView {

    public static String renderPage() {
        return String.join("\n",
            "<!DOCTYPE html>",
            "<html lang='en'>",
            "<head>",
            "<meta charset='UTF-8'>",
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>",
            "<title>Java Chatbot</title>",

            "<style>",

            "*{box-sizing:border-box}",

            "body{",
            "font-family:Arial,sans-serif;",
            "background:#eef2f7;",
            "margin:0;",
            "padding:30px 15px;",
            "}",

            ".container{",
            "max-width:500px;",
            "margin:auto;",
            "background:white;",
            "border-radius:12px;",
            "box-shadow:0 4px 15px rgba(0,0,0,0.15);",
            "overflow:hidden;",
            "}",

            ".header{",
            "background:#315efb;",
            "color:white;",
            "padding:18px 20px;",
            "display:flex;",
            "justify-content:space-between;",
            "align-items:center;",
            "}",

            ".header h2{",
            "margin:0;",
            "font-size:21px;",
            "}",

            ".status{",
            "font-size:12px;",
            "background:#ffffff33;",
            "padding:5px 9px;",
            "border-radius:12px;",
            "}",

            "#box{",
            "height:380px;",
            "overflow-y:auto;",
            "padding:18px;",
            "background:#f8f9fc;",
            "}",

            ".message{",
            "margin-bottom:14px;",
            "max-width:85%;",
            "padding:10px 13px;",
            "border-radius:12px;",
            "line-height:1.4;",
            "word-wrap:break-word;",
            "}",

            ".you{",
            "background:#dbe7ff;",
            "margin-left:auto;",
            "text-align:right;",
            "}",

            ".bot{",
            "background:#e2f5e8;",
            "margin-right:auto;",
            "}",

            ".time{",
            "font-size:10px;",
            "color:#777;",
            "margin-top:4px;",
            "}",

            ".input-area{",
            "display:flex;",
            "gap:8px;",
            "padding:15px;",
            "border-top:1px solid #ddd;",
            "background:white;",
            "}",

            "#text{",
            "flex:1;",
            "padding:11px;",
            "border:1px solid #bbb;",
            "border-radius:7px;",
            "font-size:14px;",
            "outline:none;",
            "}",

            "#text:focus{",
            "border-color:#315efb;",
            "}",

            "button{",
            "border:none;",
            "border-radius:7px;",
            "padding:10px 15px;",
            "cursor:pointer;",
            "font-weight:bold;",
            "}",

            "#sendBtn{",
            "background:#315efb;",
            "color:white;",
            "}",

            "#sendBtn:hover{",
            "background:#244bd0;",
            "}",

            "#clearBtn{",
            "background:#eee;",
            "color:#333;",
            "}",

            "#clearBtn:hover{",
            "background:#ddd;",
            "}",

            ".typing{",
            "font-size:12px;",
            "color:#777;",
            "padding:0 18px 10px;",
            "display:none;",
            "}",

            "@media(max-width:520px){",
            ".container{width:100%;}",
            "#box{height:350px;}",
            ".input-area{flex-wrap:wrap;}",
            "#text{width:100%;flex:auto;}",
            "}",

            "</style>",
            "</head>",

            "<body>",

            "<div class='container'>",

            "<div class='header'>",
            "<h2>Java Chatbot</h2>",
            "<span class='status'>Online</span>",
            "</div>",

            "<div id='box'></div>",

            "<div id='typing' class='typing'>Bot is typing...</div>",

            "<div class='input-area'>",
            "<input id='text' type='text' maxlength='500'",
            " placeholder='Type a message...'>",
            "<button id='sendBtn' onclick='send()'>Send</button>",
            "<button id='clearBtn' onclick='clearChat()'>Clear</button>",
            "</div>",

            "</div>",

            "<script>",

            "var box=document.getElementById('box');",
            "var input=document.getElementById('text');",
            "var sendBtn=document.getElementById('sendBtn');",
            "var typing=document.getElementById('typing');",

            "function getTime(){",
            " var now=new Date();",
            " return now.toLocaleTimeString([], {hour:'2-digit',minute:'2-digit'});",
            "}",

            "function add(who,text){",
            " var d=document.createElement('div');",
            " d.className='message '+who;",

            " var message=document.createElement('div');",
            " message.textContent=(who==='you'?'You: ':'Bot: ')+text;",

            " var time=document.createElement('div');",
            " time.className='time';",
            " time.textContent=getTime();",

            " d.appendChild(message);",
            " d.appendChild(time);",
            " box.appendChild(d);",
            " box.scrollTop=box.scrollHeight;",
            "}",

            "function send(){",
            " var t=input.value.trim();",

            " if(!t) return;",

            " add('you',t);",
            " input.value='';",
            " sendBtn.disabled=true;",
            " typing.style.display='block';",

            " fetch('/chat',{",
            "   method:'POST',",
            "   headers:{'Content-Type':'text/plain; charset=UTF-8'},",
            "   body:t",
            " })",

            ".then(function(response){",
            "   if(!response.ok){",
            "       throw new Error('Server error: '+response.status);",
            "   }",
            "   return response.text();",
            " })",

            ".then(function(reply){",
            "   add('bot',reply);",
            " })",

            ".catch(function(error){",
            "   add('bot','Sorry, I could not connect to the server.');",
            "   console.error(error);",
            " })",

            ".finally(function(){",
            "   sendBtn.disabled=false;",
            "   typing.style.display='none';",
            "   input.focus();",
            " });",
            "}",

            "function clearChat(){",
            " box.innerHTML='';",
            " add('bot','Chat cleared. How can I help you?');",
            " input.focus();",
            "}",

            "input.addEventListener('keydown',function(e){",
            " if(e.key==='Enter'){",
            "   send();",
            " }",
            "});",

            "add('bot','Hi! Ask me about Java. Type help for tips.');",
            "input.focus();",

            "</script>",

            "</body>",
            "</html>"
        );
    }
}

