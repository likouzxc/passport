COMMON = {};
COMMON.REGEXP = {};
COMMON.REGEXP.USERNAME=/^(?![a-z]+$)(?![A-Z]+$)[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,15}$/;
COMMON.REGEXP.MOBILE=/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$/;
COMMON.REGEXP.EMAIL=/^([a-zA-Z0-9_])+\@(([a-zA-Z0-9\-])+\.)+(cn|com|net|org|gov|cc)$/;
COMMON.REGEXP.PASSPORT=/^.{6,}$/;
COMMON.reImg =function(){
    $("img").attr("src","http://${web.host}/captcha/loginPNG.html?t="+new Date().getTime());
}