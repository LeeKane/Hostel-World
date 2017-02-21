/**
 * Created by LeeKane on 17/2/21.
 */
$(document).ready(function(){
    $("div#username").hover(function(){
        $("a#usernamebtn").removeClass("btn-hidden");
    },function(){
        $("a#usernamebtn").addClass("btn-hidden");
    });
});

$(document).ready(function(){
    $("td#vip").hover(function(){
        $("a#vipbtn").removeClass("btn-hidden");
    },function(){
        $("a#vipbtn").addClass("btn-hidden");
    });
});