import datetime
import time


def string_to_datetime(date_string):
    return datetime.datetime.strptime(date_string, "%Y-%m-%d")


def datetime_to_string(date_obj):
    return date_obj.strftime("%Y-%m-%d")


def get_first_day_of_week(date_string):
    cur_date = string_to_datetime(date_string)
    first_date = cur_date + datetime.timedelta(0 - cur_date.weekday())
    return datetime_to_string(first_date)


def get_first_day_of_month(date_string):
    cur_date = string_to_datetime(date_string)
    first_date = cur_date.replace(day=1)
    return datetime_to_string(first_date)


def get_first_day_of_year(date_string):
    cur_date = string_to_datetime(date_string)
    first_date = cur_date.replace(day=1)
    first_date = first_date.replace(month=1)
    return datetime_to_string(first_date)