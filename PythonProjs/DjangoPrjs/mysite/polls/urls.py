# from django.urls import path
# from django.urls import include
# from . import views
#
# from django.conf.urls import include
#
#
# urlpatterns = [
#     # ex: /polls/
#     path('', views.index, name='index'),
#     # ex: /polls/5/
#     path('<int:question_id>/', views.detail, name='detail'),
#     # ex: /polls/5/results/
#     path('<int:question_id>/results/', views.results, name='results'),
#     # ex: /polls/5/vote/
#     path('<int:question_id>/vote/', views.vote, name='vote'),
# ]


from django.conf.urls import url
from django.contrib import admin
from django.conf.urls import include
from . import views

urlpatterns = [
    url(r'^index/$', views.index, name='index'),
    url(r'^$', views.index, name='index'),
    url(r'^encode/$', views.encode, name='encode'),
    url(r'^user/(\w+)', views.profile, name='profile'), # capture a word of any length, so it will capture a username
    url(r'^login/$', views.login_view, name='login'), # we cannot name our view login because there's a built-in login function
    url(r'^logout/$', views.logout_view, name='logout'),
    url(r'^(\w+)/$', views.decode, name='decode'),
]


