var ddsmoothmenu={arrowimages:{down:["downarrowclass","images/arrowdrop.png",23],right:["rightarrowclass","right.gif"]},transition:{overtime:300,outtime:300},shadow:{enable:true,offsetx:5,offsety:5},showhidedelay:{showdelay:100,hidedelay:200},detectwebkit:navigator.userAgent.toLowerCase().indexOf("applewebkit")!=-1,detectie6:document.all&&!window.XMLHttpRequest,css3support:window.msPerformance||!document.all&&document.querySelector,getajaxmenu:function(a,b){var c=a("#"+b.contentsource[0]);c.html("Loading Menu...");a.ajax({url:b.contentsource[1],async:true,error:function(a){c.html("Error fetching content. Server Response: "+a.responseText)},success:function(d){c.html(d);ddsmoothmenu.buildmenu(a,b)}})},buildmenu:function(a,b){var c=ddsmoothmenu;var d=a("#"+b.mainmenuid+">ul");d.parent().get(0).className=b.classname||"ddsmoothmenu";var e=d.find("ul").parent();e.hover(function(b){a(this).children("a:eq(0)").addClass("selected")},function(b){a(this).children("a:eq(0)").removeClass("selected")});e.each(function(d){var e=a(this).css({zIndex:100-d});var f=a(this).find("ul:eq(0)").css({display:"block"});f.data("timers",{});this._dimensions={w:this.offsetWidth,h:this.offsetHeight,subulw:f.outerWidth(),subulh:f.outerHeight()};this.istopheader=e.parents("ul").length==1?true:false;f.css({top:this.istopheader&&b.orientation!="v"?this._dimensions.h+"px":0});e.children("a:eq(0)").css(this.istopheader?{paddingRight:c.arrowimages.down[2]}:{}).append('<img src="'+(this.istopheader&&b.orientation!="v"?c.arrowimages.down[1]:c.arrowimages.right[1])+'" class="'+(this.istopheader&&b.orientation!="v"?c.arrowimages.down[0]:c.arrowimages.right[0])+'" style="border:0;" />');if(c.shadow.enable&&!c.css3support){this._shadowoffset={x:this.istopheader?f.offset().left+c.shadow.offsetx:this._dimensions.w,y:this.istopheader?f.offset().top+c.shadow.offsety:e.position().top};if(this.istopheader)$parentshadow=a(document.body);else{var g=e.parents("li:eq(0)");$parentshadow=g.get(0).$shadow}this.$shadow=a('<div class="ddshadow'+(this.istopheader?" toplevelshadow":"")+'"></div>').prependTo($parentshadow).css({left:this._shadowoffset.x+"px",top:this._shadowoffset.y+"px"})}e.hover(function(d){var g=f;var h=e.get(0);clearTimeout(g.data("timers").hidetimer);g.data("timers").showtimer=setTimeout(function(){h._offsets={left:e.offset().left,top:e.offset().top};var d=h.istopheader&&b.orientation!="v"?0:h._dimensions.w;d=h._offsets.left+d+h._dimensions.subulw>a(window).width()?h.istopheader&&b.orientation!="v"?-h._dimensions.subulw+h._dimensions.w:-h._dimensions.w:d;if(g.queue().length<=1){g.css({left:d+"px",width:h._dimensions.subulw+"px"}).animate({height:"show",opacity:"show"},ddsmoothmenu.transition.overtime);if(c.shadow.enable&&!c.css3support){var f=h.istopheader?g.offset().left+ddsmoothmenu.shadow.offsetx:d;var i=h.istopheader?g.offset().top+c.shadow.offsety:h._shadowoffset.y;if(!h.istopheader&&ddsmoothmenu.detectwebkit){h.$shadow.css({opacity:1})}h.$shadow.css({overflow:"",width:h._dimensions.subulw+"px",left:f+"px",top:i+"px"}).animate({height:h._dimensions.subulh+"px"},ddsmoothmenu.transition.overtime)}}},ddsmoothmenu.showhidedelay.showdelay)},function(a){var b=f;var d=e.get(0);clearTimeout(b.data("timers").showtimer);b.data("timers").hidetimer=setTimeout(function(){b.animate({height:"hide",opacity:"hide"},ddsmoothmenu.transition.outtime);if(c.shadow.enable&&!c.css3support){if(ddsmoothmenu.detectwebkit){d.$shadow.children("div:eq(0)").css({opacity:0})}d.$shadow.css({overflow:"hidden"}).animate({height:0},ddsmoothmenu.transition.outtime)}},ddsmoothmenu.showhidedelay.hidedelay)})});if(c.shadow.enable&&c.css3support){var f=a("#"+b.mainmenuid+" ul li ul");var g=parseInt(c.shadow.offsetx)+"px "+parseInt(c.shadow.offsety)+"px 5px none";var h=["boxShadow","MozBoxShadow","WebkitBoxShadow","MsBoxShadow"];for(var i=0;i<h.length;i++){f.css(h[i],g)}}d.find("ul").css({display:"none",visibility:"visible"})},init:function(a){if(typeof a.customtheme=="object"&&a.customtheme.length==2){var b="#"+a.mainmenuid;var c=a.orientation=="v"?b:b+", "+b;document.write('<style type="text/css">\n'+c+" ul li a {background:"+a.customtheme[0]+";}\n"+b+" ul li a:hover {background:"+a.customtheme[1]+";}\n"+"</style>")}this.shadow.enable=document.all&&!window.XMLHttpRequest?false:this.shadow.enable;jQuery(document).ready(function(b){if(typeof a.contentsource=="object"){ddsmoothmenu.getajaxmenu(b,a)}else{ddsmoothmenu.buildmenu(b,a)}})}}