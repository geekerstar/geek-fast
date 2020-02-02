layui.define(function (exports) {
    exports('conf', {
        container: 'geek',
        containerBody: 'geek-body',
        v: '2.0',
        base: layui.cache.base,
        css: layui.cache.base + 'css/',
        views: layui.cache.base + 'views/',
        viewLoadBar: true,
        debug: layui.cache.debug,
        name: 'geek',
        entry: '/index',
        engine: '',
        eventName: 'geek-event',
        tableName: 'geek',
        requestUrl: './'
    })
});
