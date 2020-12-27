
var dbProducts = Vue.resource("/products");
var dbTopProducts = Vue.resource("/products/top");

connect();

Vue.component('products', {

    props: ["proda", "index"],

    data: function(){
      return {
          bgImage: "/upload/" + this.proda.imageName
      }
    },
    template: '<li :style="{ backgroundImage: `url(${bgImage})` }">{{proda.name}} {{proda.price}} {{proda.description}}<div @click="delProd(proda.id, index)"></div></li>',
    methods: {
       delProd: function(id, ind) {
         axios.post('/products/del/' + id);
         products.prod.splice(ind, 1);
         // this.changeProd();
       },
        changeProd: function () {
            products.changed();
        }

    }
});


var topProducts = new Vue({

    el: '#topProducts',
    data: {
        prod: [ ]
    },
    created() {
        creat(dbTopProducts, this.prod)
    }
});

var products = new Vue({

    el: "#products",
    data: {
        prod: [

        ],
    },
    created() {
        creat(dbProducts, this.prod)
    },
    methods: {
        changed: function () {
            changeProduct()
        }
    }

});

function creat(api, prod) {
    api.get().then(value => {
        value.json().then(data => {
            var prods = data['prods'];
            prods.forEach(d => {
                prod.push(d);
            })
        })
    });
}
