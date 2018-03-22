# # -*- coding: utf-8 -*-
# from __future__ import unicode_literals
#
# from django.db import models
#
# # Create your models here.
# from django.db import models
#
# from django.db import models
#
#
# class Question(models.Model):
#     # ...
#     def __str__(self):
#         return self.question_text
#     question_text = models.CharField(max_length=200)
#     pub_date = models.DateTimeField('date published')
#
#
# class Choice(models.Model):
#     question = models.ForeignKey(Question, on_delete=models.CASCADE)
#     choice_text = models.CharField(max_length=200)
#     votes = models.IntegerField(default=0)
#
#     # ...
#     def __str__(self):
#         return self.choice_tex
#
#



from __future__ import unicode_literals

from django.db import models
# Django actually creates a User model as part of the auth module
from django.contrib.auth.models import User

# Create your models here.
class Tinyurl(models.Model):
    user = models.ForeignKey(User)
    long_url = models.CharField(max_length=100)
    short_url = models.CharField(max_length=100)

#class User(models.Model):
#    username = models.CharField(max_length=30)
#    password = models.CharField(max_length=30)
