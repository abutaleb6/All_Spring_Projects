(function(a,b){var c=5;a.widget("ui.slider",a.ui.mouse,{widgetEventPrefix:"slide",options:{animate:false,distance:0,max:100,min:0,orientation:"horizontal",range:false,step:1,value:0,values:null},_create:function(){var b=this,d=this.options,e=this.element.find(".ui-slider-handle").addClass("ui-state-default ui-corner-all"),f="<a class='ui-slider-handle ui-state-default ui-corner-all' href='#'></a>",g=d.values&&d.values.length||1,h=[];this._keySliding=false;this._mouseSliding=false;this._animateOff=true;this._handleIndex=null;this._detectOrientation();this._mouseInit();this.element.addClass("ui-slider"+" ui-slider-"+this.orientation+" ui-widget"+" ui-widget-content"+" ui-corner-all"+(d.disabled?" ui-slider-disabled ui-disabled":""));this.range=a([]);if(d.range){if(d.range===true){if(!d.values){d.values=[this._valueMin(),this._valueMin()]}if(d.values.length&&d.values.length!==2){d.values=[d.values[0],d.values[0]]}}this.range=a("<div></div>").appendTo(this.element).addClass("ui-slider-range"+" ui-widget-header"+(d.range==="min"||d.range==="max"?" ui-slider-range-"+d.range:""))}for(var i=e.length;i<g;i+=1){h.push(f)}this.handles=e.add(a(h.join("")).appendTo(b.element));this.handle=this.handles.eq(0);this.handles.add(this.range).filter("a").click(function(a){a.preventDefault()}).hover(function(){if(!d.disabled){a(this).addClass("ui-state-hover")}},function(){a(this).removeClass("ui-state-hover")}).focus(function(){if(!d.disabled){a(".ui-slider .ui-state-focus").removeClass("ui-state-focus");a(this).addClass("ui-state-focus")}else{a(this).blur()}}).blur(function(){a(this).removeClass("ui-state-focus")});this.handles.each(function(b){a(this).data("index.ui-slider-handle",b)});this.handles.keydown(function(d){var e=a(this).data("index.ui-slider-handle"),f,g,h,i;if(b.options.disabled){return}switch(d.keyCode){case a.ui.keyCode.HOME:case a.ui.keyCode.END:case a.ui.keyCode.PAGE_UP:case a.ui.keyCode.PAGE_DOWN:case a.ui.keyCode.UP:case a.ui.keyCode.RIGHT:case a.ui.keyCode.DOWN:case a.ui.keyCode.LEFT:d.preventDefault();if(!b._keySliding){b._keySliding=true;a(this).addClass("ui-state-active");f=b._start(d,e);if(f===false){return}}break}i=b.options.step;if(b.options.values&&b.options.values.length){g=h=b.values(e)}else{g=h=b.value()}switch(d.keyCode){case a.ui.keyCode.HOME:h=b._valueMin();break;case a.ui.keyCode.END:h=b._valueMax();break;case a.ui.keyCode.PAGE_UP:h=b._trimAlignValue(g+(b._valueMax()-b._valueMin())/c);break;case a.ui.keyCode.PAGE_DOWN:h=b._trimAlignValue(g-(b._valueMax()-b._valueMin())/c);break;case a.ui.keyCode.UP:case a.ui.keyCode.RIGHT:if(g===b._valueMax()){return}h=b._trimAlignValue(g+i);break;case a.ui.keyCode.DOWN:case a.ui.keyCode.LEFT:if(g===b._valueMin()){return}h=b._trimAlignValue(g-i);break}b._slide(d,e,h)}).keyup(function(c){var d=a(this).data("index.ui-slider-handle");if(b._keySliding){b._keySliding=false;b._stop(c,d);b._change(c,d);a(this).removeClass("ui-state-active")}});this._refreshValue();this._animateOff=false},destroy:function(){this.handles.remove();this.range.remove();this.element.removeClass("ui-slider"+" ui-slider-horizontal"+" ui-slider-vertical"+" ui-slider-disabled"+" ui-widget"+" ui-widget-content"+" ui-corner-all").removeData("slider").unbind(".slider");this._mouseDestroy();return this},_mouseCapture:function(b){var c=this.options,d,e,f,g,h,i,j,k,l;if(c.disabled){return false}this.elementSize={width:this.element.outerWidth(),height:this.element.outerHeight()};this.elementOffset=this.element.offset();d={x:b.pageX,y:b.pageY};e=this._normValueFromMouse(d);f=this._valueMax()-this._valueMin()+1;h=this;this.handles.each(function(b){var c=Math.abs(e-h.values(b));if(f>c){f=c;g=a(this);i=b}});if(c.range===true&&this.values(1)===c.min){i+=1;g=a(this.handles[i])}j=this._start(b,i);if(j===false){return false}this._mouseSliding=true;h._handleIndex=i;g.addClass("ui-state-active").focus();k=g.offset();l=!a(b.target).parents().andSelf().is(".ui-slider-handle");this._clickOffset=l?{left:0,top:0}:{left:b.pageX-k.left-g.width()/2,top:b.pageY-k.top-g.height()/2-(parseInt(g.css("borderTopWidth"),10)||0)-(parseInt(g.css("borderBottomWidth"),10)||0)+(parseInt(g.css("marginTop"),10)||0)};if(!this.handles.hasClass("ui-state-hover")){this._slide(b,i,e)}this._animateOff=true;return true},_mouseStart:function(a){return true},_mouseDrag:function(a){var b={x:a.pageX,y:a.pageY},c=this._normValueFromMouse(b);this._slide(a,this._handleIndex,c);return false},_mouseStop:function(a){this.handles.removeClass("ui-state-active");this._mouseSliding=false;this._stop(a,this._handleIndex);this._change(a,this._handleIndex);this._handleIndex=null;this._clickOffset=null;this._animateOff=false;return false},_detectOrientation:function(){this.orientation=this.options.orientation==="vertical"?"vertical":"horizontal"},_normValueFromMouse:function(a){var b,c,d,e,f;if(this.orientation==="horizontal"){b=this.elementSize.width;c=a.x-this.elementOffset.left-(this._clickOffset?this._clickOffset.left:0)}else{b=this.elementSize.height;c=a.y-this.elementOffset.top-(this._clickOffset?this._clickOffset.top:0)}d=c/b;if(d>1){d=1}if(d<0){d=0}if(this.orientation==="vertical"){d=1-d}e=this._valueMax()-this._valueMin();f=this._valueMin()+d*e;return this._trimAlignValue(f)},_start:function(a,b){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);c.values=this.values()}return this._trigger("start",a,c)},_slide:function(a,b,c){var d,e,f;if(this.options.values&&this.options.values.length){d=this.values(b?0:1);if(this.options.values.length===2&&this.options.range===true&&(b===0&&c>d||b===1&&c<d)){c=d}if(c!==this.values(b)){e=this.values();e[b]=c;f=this._trigger("slide",a,{handle:this.handles[b],value:c,values:e});d=this.values(b?0:1);if(f!==false){this.values(b,c,true)}}}else{if(c!==this.value()){f=this._trigger("slide",a,{handle:this.handles[b],value:c});if(f!==false){this.value(c)}}}},_stop:function(a,b){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);c.values=this.values()}this._trigger("stop",a,c)},_change:function(a,b){if(!this._keySliding&&!this._mouseSliding){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);c.values=this.values()}this._trigger("change",a,c)}},value:function(a){if(arguments.length){this.options.value=this._trimAlignValue(a);this._refreshValue();this._change(null,0);return}return this._value()},values:function(b,c){var d,e,f;if(arguments.length>1){this.options.values[b]=this._trimAlignValue(c);this._refreshValue();this._change(null,b);return}if(arguments.length){if(a.isArray(arguments[0])){d=this.options.values;e=arguments[0];for(f=0;f<d.length;f+=1){d[f]=this._trimAlignValue(e[f]);this._change(null,f)}this._refreshValue()}else{if(this.options.values&&this.options.values.length){return this._values(b)}else{return this.value()}}}else{return this._values()}},_setOption:function(b,c){var d,e=0;if(a.isArray(this.options.values)){e=this.options.values.length}a.Widget.prototype._setOption.apply(this,arguments);switch(b){case"disabled":if(c){this.handles.filter(".ui-state-focus").blur();this.handles.removeClass("ui-state-hover");this.handles.propAttr("disabled",true);this.element.addClass("ui-disabled")}else{this.handles.propAttr("disabled",false);this.element.removeClass("ui-disabled")}break;case"orientation":this._detectOrientation();this.element.removeClass("ui-slider-horizontal ui-slider-vertical").addClass("ui-slider-"+this.orientation);this._refreshValue();break;case"value":this._animateOff=true;this._refreshValue();this._change(null,0);this._animateOff=false;break;case"values":this._animateOff=true;this._refreshValue();for(d=0;d<e;d+=1){this._change(null,d)}this._animateOff=false;break}},_value:function(){var a=this.options.value;a=this._trimAlignValue(a);return a},_values:function(a){var b,c,d;if(arguments.length){b=this.options.values[a];b=this._trimAlignValue(b);return b}else{c=this.options.values.slice();for(d=0;d<c.length;d+=1){c[d]=this._trimAlignValue(c[d])}return c}},_trimAlignValue:function(a){if(a<=this._valueMin()){return this._valueMin()}if(a>=this._valueMax()){return this._valueMax()}var b=this.options.step>0?this.options.step:1,c=(a-this._valueMin())%b,d=a-c;if(Math.abs(c)*2>=b){d+=c>0?b:-b}return parseFloat(d.toFixed(5))},_valueMin:function(){return this.options.min},_valueMax:function(){return this.options.max},_refreshValue:function(){var b=this.options.range,c=this.options,d=this,e=!this._animateOff?c.animate:false,f,g={},h,i,j,k;if(this.options.values&&this.options.values.length){this.handles.each(function(b,i){f=(d.values(b)-d._valueMin())/(d._valueMax()-d._valueMin())*100;g[d.orientation==="horizontal"?"left":"bottom"]=f+"%";a(this).stop(1,1)[e?"animate":"css"](g,c.animate);if(d.options.range===true){if(d.orientation==="horizontal"){if(b===0){d.range.stop(1,1)[e?"animate":"css"]({left:f+"%"},c.animate)}if(b===1){d.range[e?"animate":"css"]({width:f-h+"%"},{queue:false,duration:c.animate})}}else{if(b===0){d.range.stop(1,1)[e?"animate":"css"]({bottom:f+"%"},c.animate)}if(b===1){d.range[e?"animate":"css"]({height:f-h+"%"},{queue:false,duration:c.animate})}}}h=f})}else{i=this.value();j=this._valueMin();k=this._valueMax();f=k!==j?(i-j)/(k-j)*100:0;g[d.orientation==="horizontal"?"left":"bottom"]=f+"%";this.handle.stop(1,1)[e?"animate":"css"](g,c.animate);if(b==="min"&&this.orientation==="horizontal"){this.range.stop(1,1)[e?"animate":"css"]({width:f+"%"},c.animate)}if(b==="max"&&this.orientation==="horizontal"){this.range[e?"animate":"css"]({width:100-f+"%"},{queue:false,duration:c.animate})}if(b==="min"&&this.orientation==="vertical"){this.range.stop(1,1)[e?"animate":"css"]({height:f+"%"},c.animate)}if(b==="max"&&this.orientation==="vertical"){this.range[e?"animate":"css"]({height:100-f+"%"},{queue:false,duration:c.animate})}}}});a.extend(a.ui.slider,{version:"1.8.18"})})(jQuery)