layui.extend({
    geek: 'lay/modules/geek',
    validate: 'lay/modules/validate'
}).define(['geek', 'conf'], function (exports) {
    layui.geek.initPage();
    console.log("\n 🐤", "color: #fff; font-size: .84rem;background: #366ed8; padding:5px 0;", "font-size: .84rem;background: #fff; border: 2px solid #b0e0a8;border-left: none; padding:3px 0;", " font-size: .84rem;background: #fcf9ec; padding:5px 0;margin-left: 8px");
    exports('index', {});
});
