# Vue  +  Element-UI

## 1. 基本语法：

**MVVM模式： Model  View  ViewModel **  （监听Dom和数据双向绑定）**

### CDN:

```html
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
```

{{ message}}       v-bind = 

```html
body>
<!--view层-->
<div id="app">
    <span v-bind:title="message">
        悬停查看信息
    </span>
</div>

    <script>
        var vm = new Vue({
            el:"#app",
            data:{
                message:"Hello!"
            }
        });
    </script>
</body>
```

### 判断语句：v-if    v-else-if   v-else

```html
<div v-if="type === 'A'">
  A
</div>
<div v-else-if="type === 'B'">
  B
</div>
<div v-else-if="type === 'C'">
  C
</div>
<div v-else>
  Not A/B/C
</div>
```

### 循环语句：v-for

```html
body>
<!--view层-->
<div id="app">
    <li v-for="item in items">
        {{item.message}}
    </li>
</div>

<script>
    var vm = new Vue({
        el:"#app",
        data:{
           items:[
               {message: "dsad"},
               {message: "dsadasd"},
               {message:"qww"}
               ]
        }
    });
</script>
</body>
```

### Methods:   v-on

方法必须定义在Vue的methods中,通过 v-on：click等事件!

```html
<body>
<!--view层-->
<div id="app">
    <button v-on:click="sayHi">点我</button>
</div>

<script>
    var vm = new Vue({
        el:"#app",
        data:{
            message:"hhh"
        },
        methods:{ //方法必须定义在Vue的methods中,通过 v-on：click等事件!
            sayHi: function (event) {
                // `this` 在方法里指向当前 Vue 实例
                alert(this.message)
            }
        }
    });
</script>
</body>
```

### 自定义事件分发

​	this.$emti("事件名"，"参数")

```html
<body>
<div id="app">
   <ccc>
       <w-title slot="w-title" v-bind:title="title"></w-title>
       <w-items slot="w-items" v-for="(item,index) in items" v-bind:items="item"
                :index="index" v-on:remove="removeItems"></w-items>
   </ccc>
</div>


<script>
    Vue.component("ccc",{  //组件的 name <ws></ws>  插槽
        template:'<div>\
                    <slot name="w-title"></slot>\
                    <ul>\
                        <slot name="w-items"></slot>\
                    </ul>\
            </div>'
    });
    Vue.component("w-title",{
        props:['title'],  //bind的数据
        template:'<div>{{title}}</div>'
    });
    Vue.component("w-items",{
        props:['items','index'],  //bind的数据
        template:'<li>{{index}}--->{{items}}<button v-on:click="remove">操作</button></li>',
        methods:{
            remove: function (index) {
                this.$emit("remove",index)
            }
        }
    });
    var vm = new Vue({
        el:"#app",
        data:{
            title:"keuceng",
            items:["d","s","w","f"]
        },
        methods:{
            removeItems: function (index) {
                this.items.splice(index,1);
            }
        }

    });
</script>
</body>
```



## 2. 网络通信(	Axios)

CDN:

```html
<script src="https://cdn.bootcdn.net/ajax/libs/axios/0.19.2/axios.min.js"></script>
```

示例：

```html
<div id="app">
    {{info.name}}
    {{info.adress.city}}
</div>
    <script type="text/javascript">
        var vm = new Vue({
            el: '#app',
            data(){  //和extjs的store的field一样
                return {      // 属性必须和json数据相同！
                    info: {
                        name: null,
                        adress: {
                            street: null,
                            city: null
                        }
                    }
                }
            },
            mounted(){  //钩子函数  链式编程  ES6的特性
                axios.get("data.json").then(response=>(this.info=response.data))
            }

        })

    </script>
</body>
```

## 3. 安装脚手架和webpack

## 4. vue-router(路由)