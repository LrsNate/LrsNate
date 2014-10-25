[1mdiff --git a/public/part/anime-watching.html b/public/part/anime-watching.html[m
[1mindex 4fde7ac..020a0d7 100644[m
[1m--- a/public/part/anime-watching.html[m
[1m+++ b/public/part/anime-watching.html[m
[36m@@ -1,7 +1,7 @@[m
 <div class="container-fluid" ng-switch on="aw_status">[m
   <div ng-switch-when="ok">[m
     <accordion close-others="true">[m
[31m-      <accordion-group ng-repeat="anime in animelist" is-open="status.open">[m
[32m+[m[32m      <accordion-group ng-repeat="anime in animelist | orderBy:'last_watched':true" is-open="status.open">[m
         <accordion-heading>[m
           <div class="aw-entry" ng-class="{'aw-entry-first': $first}">[m
               <div class="aw-caret">[m
[36m@@ -17,7 +17,7 @@[m
               <div class="aw-progress">[m
                 <div class="aw-box">[m
                   <p class="aw-text">[m
[31m-                    {{ anime.episodes_watched}} /[m
[32m+[m[32m                    {{ anime.episodes_watched }} /[m
                     <span ng-if="anime.anime.episode_count > 0">{{ anime.anime.episode_count }}</span>[m
                     <span ng-if="anime.anime.episode_count == 0">?</span>[m
                   </p>[m
