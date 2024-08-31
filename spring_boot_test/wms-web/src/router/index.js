import VueRouter from 'vue-router';
const routes=[
{
        path:'/',
        name:'db_login',
        component:()=>import('../components/db_login')
},
    {
        path:'/MM',
        name:'MM',
        component:()=>import('../components/MM'),
        children:[
            {
                path:'/db_Home',
                name:'db_Home',
                meta:{
                    title:'首页'
                },
                component:()=>import('../components/db_Home'),
            },
            /*{
                path:'/Admin',
                name:'admin',
                meta:{
                    title:'管理员管理'
                },
                component:()=>import('../components/admin/AdminMange'),
            },
            {
                path:'/User',
                name:'user',
                meta:{
                    title:'用户管理'
                },
                component:()=>import('../components/user/UserMange'),
            },*/
        ]
    }
]
const router=new VueRouter({
    mode:'history',
    routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'history',
        routes: []
    }).matcher
}

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err);
}
export  default  router;