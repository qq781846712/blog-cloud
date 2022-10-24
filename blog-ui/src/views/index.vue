<template>
  <!-- header -->
  <header class="header header-mobile index-header" :class="{'header-sidebar-active' : isOpen}">
    <!-- top read progress line -->
    <div class="header-element">
      <div class="read-progress"></div>
    </div>
    <!-- sidebar menu button -->
    <div class="header-element">
      <div class="header-sidebar-menu" :class="{'header-sidebar-menu-active' : isOpen}" @click="isOpen = !isOpen">
        <div style="padding-left: 1px;">&#xe775;</div>
      </div>
    </div>
    <!-- header actions -->
    <div class="header-actions">
      <!-- theme mode switch button -->
      <span class="header-theme-btn header-element">
            <i class="fas fa-adjust"></i>
        </span>
      <!-- back to home page text -->
      <span class="home-link header-element">
            <a href="/">Blank Blog.</a>
        </span>
    </div>
    <!-- toggle banner for post layout -->
  </header>
  <!-- fixed footer -->
  <footer class="footer-fixed index-footer-fixed" :class="{'footer-fixed-sidebar-active' : isOpen}">
    <!-- back to top button -->
    <div class="footer-fixed-element">
      <div class="back-top back-top-hidden">
        <div>&#xe639;</div>
      </div>
    </div>
  </footer>
  <!-- wrapper -->
  <div class="wrapper" :class="{'wrapper-sidebar-active' : isOpen}">
    <div class="site-intro" style="height:50vh;">
      <!-- 主页  -->
      <!-- 文章页  -->
      <div class="site-intro-placeholder"></div>
      <div class="site-intro-img" style="background-image: url(/intro/index-bg.jpg)"></div>
      <div class="site-intro-meta">
        <!-- 标题  -->
        <h1 class="intro-title intro-fade-in">
          <!-- 主页  -->
          Blank Blog.
          <!-- 文章页  -->
        </h1>
        <!-- 副标题 -->
        <p class="intro-subtitle intro-fade-in">
          <!-- 主页副标题  -->
          it's better to burn out than to fade away
          <!-- 文章页  -->
        </p>
        <!-- 文章页 meta -->
      </div>
    </div>
<!--   加载 -->
<!--    <img class="loading" src="/public/assets/loading.svg"
         style="display: block; margin: 6rem auto 0 auto; width: 6rem; height: 6rem;"/>-->
    <div class="container">
      <main class="main index-page">
        <article class="index-post">
          <a class="abstract-title" href="/2022/10/24/hello-world/">
            <span class="abstract-title-text">Hello World</span>
          </a>
          <div class="abstract-content">
            Welcome to Hexo! This is your very first post. Check documentation for more info. If you get any
            problems when using Hexo, you can find the answer in troubleshooting or you can ask me on GitHub.
            Quick StartCreate a new post1$ hexo new &quot;My New Post&quot;

            More info: Writing
            Run server1$ hexo ...
          </div>
          <!-- read more -->
          <div class="abstract-post-meta">
            <!-- date -->
            <div class="abstract-date" title="Last updated: 2022/10/24">
              <span class="abstract-calander iconfont-archer">&#xe676;</span><span class="abstract-time">2022/10/24</span>
            </div>
            <!-- tags -->
          </div>
        </article>
        <div class="index-post-divider"></div>
        <!-- paginator  -->
      </main>
      <!-- profile -->
      <div class="profile">
        <img class="profile-avatar" alt="avatar" src="/public/avatar/Misaka.jpg">
        <div class="profile-name">blank</div>
        <div class="profile-signature">
          witness me
        </div>
        <div class="profile-social">
          <a href="mailto:781846712@qq.com" class="iconfont-archer email" title=email></a>
          <a href="https//github.com/qq781846712" class="iconfont-archer github" target="_blank" title=github></a>
          <span class="iconfont-archer wechat" title=wechat>
            <img class="profile-qr" src="/public/assets/example_qr.png"/>
          </span>
        </div>
        <div class="profile-link-item">
          <a href="/about" class="about-me">
            ABOUT ME
          </a>
        </div>
      </div>
    </div>
    <footer class="footer footer-unloaded">
      <!-- social  -->
      <div class="social">
        <a href="mailto:781846712@qq.com" class="iconfont-archer email" title=email></a>
        <a href="https//github.com/qq781846712" class="iconfont-archer github" target="_blank" title=github></a>
        <span class="iconfont-archer wechat" title=wechat>
        <img class="profile-qr" src="/public/assets/example_qr.png"/>
        </span>
      </div>
    </footer>
  </div>
  <!-- toc -->

  <!-- sidebar -->
  <div class="sidebar ps" :class="{'sidebar-hide' : !isOpen, 'sidebar-active' : isOpen}">
    <ul class="sidebar-tabs" :class="'sidebar-tabs-active-' + tabIndex">
      <li class="sidebar-tab-archives" @click="tabIndex = 0"><span class="iconfont-archer">&#xe67d;</span><span
          class="tab-name">Archive</span></li>
      <li class="sidebar-tab-tags" @click="tabIndex = 1"><span class="iconfont-archer">&#xe61b;</span><span class="tab-name">Tag</span></li>
      <li class="sidebar-tab-categories" @click="tabIndex = 2"><span class="iconfont-archer">&#xe666;</span><span
          class="tab-name">Cate</span></li>
    </ul>
    <div class="sidebar-content sidebar-content-show-archive" :class="'sidebar-content-active-' + tabIndex">
      <div class="sidebar-panel-archives">
        <!-- 在 ejs 中将 archive 按照时间排序 -->
        <div class="total-and-search">
          <div class="total-archive">
            Total : 1
          </div>
          <!-- search  -->
        </div>
        <div class="post-archive">
          <div class="archive-year"> 2022</div>
          <ul class="year-list">
            <li class="archive-post-item">
              <span class="archive-post-date">10/24</span>
              <a class="archive-post-title" href="/2022/10/24/hello-world/">Hello World</a>
            </li>
          </ul>
        </div>
      </div>
      <div class="sidebar-panel-tags">
        <div class="sidebar-tags-name">
        </div>
        <div class="iconfont-archer sidebar-tags-empty">&#xe678;</div>
        <div class="tag-load-fail" style="display: none; color: #ccc; font-size: 0.6rem;">
          缺失模块，请参考主题文档进行安装配置：https://github.com/fi3ework/hexo-theme-archer#%E5%AE%89%E8%A3%85%E4%B8%BB%E9%A2%98
        </div>
        <div class="sidebar-tags-list"></div>
      </div>
      <div class="sidebar-panel-categories">
        <div class="sidebar-categories-name">
        </div>
        <div class="iconfont-archer sidebar-categories-empty">&#xe678;</div>
        <div class="sidebar-categories-list"></div>
      </div>

    </div>
  </div>
</template>
<script lang="ts" setup>
import '/public/css/style.css?v=20211217'
import {ref} from "vue";

const isOpen = ref(false);
const tabIndex = ref(0);
</script>

<style>
@font-face {
  font-family: 'Oswald-Regular';
  src: url("/public/font/Oswald-Regular.ttf");
}

body {
  margin: 0;
}

.site-intro {
  position: relative;
  z-index: 3;
  width: 100%;
  overflow: hidden;
}

.site-intro-placeholder {
  position: absolute;
  z-index: -2;
  top: 0;
  left: 0;
  width: calc(100% + 300px);
  height: 100%;
  background: repeating-linear-gradient(-45deg, #444 0, #444 80px, #333 80px, #333 160px);
  background-position: center center;
  transform: translate3d(-226px, 0, 0);
  animation: gradient-move 2.5s ease-out 0s infinite;
}

@keyframes gradient-move {
  0% {
    transform: translate3d(-226px, 0, 0);
  }
  100% {
    transform: translate3d(0, 0, 0);
  }
}
</style>
