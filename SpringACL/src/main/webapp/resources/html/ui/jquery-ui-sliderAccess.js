(function(a){a.fn.extend({sliderAccess:function(b){b=b||{};b.touchonly=b.touchonly!==undefined?b.touchonly:true;if(b.touchonly===true&&!("ontouchend"in document))return a(this);return a(this).each(function(c,d){var e=a(this),f=a.extend({},{where:"after",step:e.slider("option","step"),upIcon:"ui-icon-plus",downIcon:"ui-icon-minus",text:false,upText:"+",downText:"-",buttonset:true,buttonsetTag:"span"},b),g=a("<"+f.buttonsetTag+' class="ui-slider-access">'+'<button data-icon="'+f.downIcon+'" data-step="-'+f.step+'">'+f.downText+"</button>"+'<button data-icon="'+f.upIcon+'" data-step="'+f.step+'">'+f.upText+"</button>"+"</"+f.buttonsetTag+">");g.children("button").each(function(b,c){var d=a(this);d.button({text:f.text,icons:{primary:d.data("icon")}}).click(function(a){var b=d.data("step"),c=e.slider("value"),f=c+=b*1,g=e.slider("option","min"),h=e.slider("option","max");a.preventDefault();if(f<g||f>h)return;e.slider("value",f);e.slider("option","slide").call(e,null,{value:f})})});e[f.where](g);if(f.buttonset){g.removeClass("ui-corner-right").removeClass("ui-corner-left").buttonset();g.eq(0).addClass("ui-corner-left");g.eq(1).addClass("ui-corner-right")}var h=g.css({marginLeft:f.where=="after"?10:0,marginRight:f.where=="before"?10:0}).outerWidth(true)+5;var i=e.outerWidth(true);e.css("display","inline-block").width(i-h)})}})})(jQuery)