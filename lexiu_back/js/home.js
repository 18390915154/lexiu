/**
 * Created by my on 2017/4/19.
 */
$(document).ready(function () {
        var config = {
            vx: 4,
            vy:  4,
            height: 2,
            width: 2,
            count: 100,
            color: "121, 162, 185",
            stroke: "100,200,180",
            dist: 6000,
            e_dist: 20000,
            max_conn: 10
        };
        CanvasParticle(config);
});
