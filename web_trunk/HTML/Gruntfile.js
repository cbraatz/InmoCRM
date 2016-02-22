module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        directory: {
            app: 'app',
            dist: 'html'
        },

        watch: {
            css: {
                files: [
                    '<%= directory.app %>/css/modules/*.scss',
                    '<%= directory.app %>/css/modules/animation/*.scss',
                    '<%= directory.app %>/css/*.scss',
                    '<%= directory.app %>/css/*.css'
                ],
                tasks: ['sass:dev', 'copy:styles'],
                options: {
                    spawn: false,
                    livereload: true
                }
            },
            includeTpl: {
                files: ['<%= directory.app %>/**/*.html'],
                tasks: ['includereplace'],
                options: {
                    spawn: false,
                    livereload: true
                }
            },
            configFiles: {
                files: [ 'Gruntfile.js'],
                options: {
                    reload: true
                }
            }
        },

        includereplace: {
            dynamic_mappings: {
                files: [
                    {
                        expand: true,     // Enable dynamic expansion.
                        src: [
                            '<%= directory.app %>/**/*.html',
                            '!<%= directory.app %>/templates/*.html',
                            '!<%= directory.app %>/shared/*.html',
                            '!<%= directory.app %>/shared/**/*.html'
                        ],
                        dest: '<%= directory.dist %>',
                        flatten: true
                    }
                ]
            }
        },

        sass: {
            dev: {
                src: '<%= directory.app %>/css/color_themes/default.scss',
                dest: '<%= directory.dist %>/css/main-default.css'
            },
            'color-cyan':{
                src: '<%= directory.app %>/css/color_themes/cyan.scss',
                dest: '<%= directory.dist %>/css/main-cyan.css'
            },
            'color-red':{
                src: '<%= directory.app %>/css/color_themes/red.scss',
                dest: '<%= directory.dist %>/css/main-red.css'
            },
            'color-yellow':{
                src: '<%= directory.app %>/css/color_themes/yellow.scss',
                dest: '<%= directory.dist %>/css/main-yellow.css'
            },
            'color-green':{
                src: '<%= directory.app %>/css/color_themes/green.scss',
                dest: '<%= directory.dist %>/css/main-green.css'
            },
            'color-blue':{
                src: '<%= directory.app %>/css/color_themes/blue.scss',
                dest: '<%= directory.dist %>/css/main-blue.css'
            }
        },

        copy: {
            // Create index file(copy homepage-1-index.html)
            html: {
                src: '<%= directory.dist %>/homepage-1-index.html',
                dest: '<%= directory.dist %>/index.html'
            },
            styles: {
                expand: true,
                src: [
                    '<%= directory.app %>/css/vendor/*.css',
                    '<%= directory.app %>/css/modules/animation/*.css'
                ],
                dest: '<%= directory.dist %>/css/',
                flatten: true,
                filter: 'isFile'
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-include-replace');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-concat-css');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('default', ['includereplace', 'sass', 'copy']);
    grunt.registerTask('watchfiles', ['watch']);
};