var animeControllers = angular.module('animeControllers', [
    'navbarServices',
    'ui.bootstrap',
    'restServices'
]);

animeControllers.controller('animeWatchingCtrl', [
    '$scope',
    'navbarService',
    'restService',
    function ($scope, navbarService, restService) {
        navbarService.setPage('anime');

        $scope.animeWatchingLoad = function () {
            $scope.aw_status = "loading";
            $scope.animelist = {};
            restService.getAnimelists(function (data, status) {
                if (status / 100 == 2) {
                    $scope.anime = data;
                    $scope.aw_status = "ok";
                }
                else {
                    $scope.aw_status = "error";
                }
            });/*
            $scope.animelist = [{"id":7213942,"episodes_watched":3,"last_watched":"2014-10-20T18:49:48.675Z","updated_at":"2014-10-20T18:49:48.667Z","rewatched_times":0,"notes":"","notes_present":false,"status":"currently-watching","private":false,"rewatching":false,"anime":{"id":1801,"slug":"tengen-toppa-gurren-lagann","status":"Finished Airing","url":"https://hummingbird.me/anime/tengen-toppa-gurren-lagann","title":"Tengen Toppa Gurren Lagann","alternate_title":"","episode_count":27,"cover_image":"https://static.hummingbird.me/anime/poster_images/000/001/801/large/iPjyOSxwKvoWm.jpg?1409433948","synopsis":"In a far away future, mankind lives underground in huge caves, unknowing of a world above with a sky and stars.\r\nIn the small village of Jiha, Simon, a shy boy who works as a digger discovers a strange glowing object during excavation. The enterprising Kamina, a young man with a pair of rakish sunglasses and the passion of a firey sun, befriends Simon and forms a small band of brothers, the Gurren Brigade, to escape the village and break through the ceiling of the cave to reach the surface, which few believe exist.\r\nThe village elder won't hear of such foolishness and punishes the Brigade. However, when disaster strikes from the world above and the entire village is in jeopardy, it's up to Simon, Kamina, a girl with a big gun named Yoko, and the small yet sturdy robot, Lagann, to save the day.\r\nThe new friends journey to the world above and find that the surface is a harsh battlefield, and it's up to them to fight back against the rampaging Beastmen to turn the tide in the humans' favor! Pierce the heavens, Gurren Lagann!","show_type":"TV","started_airing":"2007-04-01","finished_airing":"2007-09-30"},"rating":{"type":"simple","value":null}},{"id":7213582,"episodes_watched":15,"last_watched":"2014-10-20T18:51:49.482Z","updated_at":"2014-10-20T18:51:49.483Z","rewatched_times":0,"notes":null,"notes_present":null,"status":"currently-watching","private":false,"rewatching":false,"anime":{"id":8174,"slug":"sword-art-online-ii","status":"Currently Airing","url":"https://hummingbird.me/anime/sword-art-online-ii","title":"Sword Art Online II","alternate_title":"","episode_count":24,"cover_image":"https://static.hummingbird.me/anime/poster_images/000/008/174/large/647561.jpg?1409627149","synopsis":"One year after the SAO incident, Kirito is approached by Seijiro Kikuoka from Japan's Ministry of Internal Affairs and Communications Department \"VR Division\" with a rather peculiar request.\r\n\r\nThat was an investigation on the \"Death Gun\" incident that occurred in the gun and steel filled VRMMO called Gun Gale Online (GGO). \"Players who are shot by a mysterious avatar with a jet black gun lose their lives even in the real world...\" Failing to turn down Kikuoka's bizarre request, Kirito logs in to GGO even though he is not completely convinced that the virtual world could physically affect the real world.\r\n\r\nKirito wanders in an unfamiliar world in order to gain any clues about the \"Death Gun.\" Then, a female sniper named Sinon who owns a gigantic \"Hecate II\" rifle extends Kirito a helping hand. With Sinon's help, Kirito decides to enter the \"Bullet of Bullets,\" a large tournament to choose the most powerful gunner within the realm of GGO, in hopes to become the target of the \"Death Gun\" and make direct contact with the mysterious avatar.\r\n\r\n(Source: Crunchyroll)","show_type":"TV","started_airing":"2014-07-05","finished_airing":null},"rating":{"type":"simple","value":null}},{"id":7213552,"episodes_watched":7,"last_watched":"2014-10-20T18:31:29.469Z","updated_at":"2014-10-20T18:49:48.157Z","rewatched_times":0,"notes":"","notes_present":false,"status":"currently-watching","private":false,"rewatching":false,"anime":{"id":147,"slug":"shingetsutan-tsukihime","status":"Finished Airing","url":"https://hummingbird.me/anime/shingetsutan-tsukihime","title":"Shingetsutan Tsukihime","alternate_title":"Lunar Legend Tsukihime","episode_count":12,"cover_image":"https://static.hummingbird.me/anime/poster_images/000/000/147/large/147.jpg?1408440797","synopsis":"Shiki Tohno sustained a life threatening injury as a child, and due to that incident he was sent away from the Tohno household and was given to a relative to be raised. Years later, when Shiki is in high school, the head of the Tohno household—his father—dies, and he is ordered to move back in by his sister Akiha, who is the new head of the household. However, Shiki holds a huge secret. Ever since that injury, he has been seeing lines on objects, and only with a special pair of glasses is he able to stop seeing them. Also he is unable to remember anything well from the time before his accident. The day he moves back to the Tohno household is the day he stumbles upon a woman named Arcueid Brunstud and decapitates her with one stab of his knife in a temporary fit of insanity. When she suddenly showed up beside him later alive and well, and ask him to be her bodyguard, Shiki's journey to unravel the mysteries of his past begins.\n(Source: ANN)","show_type":"TV","started_airing":"2003-10-10","finished_airing":"2003-12-26"},"rating":{"type":"simple","value":null}},{"id":7212922,"episodes_watched":2,"last_watched":"2014-10-20T17:41:49.517Z","updated_at":"2014-10-20T18:49:45.822Z","rewatched_times":0,"notes":"","notes_present":false,"status":"currently-watching","private":false,"rewatching":false,"anime":{"id":7882,"slug":"fate-stay-night-unlimited-blade-works-2014","status":"Currently Airing","url":"https://hummingbird.me/anime/fate-stay-night-unlimited-blade-works-2014","title":"Fate/stay night: Unlimited Blade Works (2014)","alternate_title":"","episode_count":0,"cover_image":"https://static.hummingbird.me/anime/poster_images/000/007/882/large/IERBT2u.jpg?1412704065","synopsis":"New anime adaptation of Fate/stay night, following the Unlimited Blade Works route. ","show_type":"TV","started_airing":"2014-10-12","finished_airing":null},"rating":{"type":"simple","value":null}}];
            $scope.animewatching_status = "ok";
            */
        };
        $scope.animeWatchingLoad();
    }
]);

animeControllers.filter('slice', function () {
    return function (arr, start, end) {
        return arr.slice(start, end);
    };
});

animeControllers.config(function($provide) {
    $provide.decorator('accordionDirective', function($delegate) {
        $delegate[0].templateUrl = "/assets/tpl/accordion.html";
        return $delegate;
    });
    $provide.decorator('accordionGroupDirective', function($delegate) {
        $delegate[0].templateUrl = "/assets/tpl/accordion-group.html";
        return $delegate;
    });
});