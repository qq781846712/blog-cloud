import { type RouteRecordRaw, createRouter, createWebHashHistory, createWebHistory } from "vue-router"

/** 常驻路由 */
export const constantRoutes: RouteRecordRaw[] = [
    {
        path: "/",
        component: () => import("@/views/index.vue"),
        meta: {
            hidden: true
        }
    }
]

const router = createRouter({
    history:
        import.meta.env.VITE_ROUTER_HISTORY === "hash"
            ? createWebHashHistory(import.meta.env.VITE_PUBLIC_PATH)
            : createWebHistory(import.meta.env.VITE_PUBLIC_PATH),
    routes: constantRoutes
})

export default router