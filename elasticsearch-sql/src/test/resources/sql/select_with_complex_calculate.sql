select (((jvm_usage / 10) + (cpu_usage * 12)) / historical_load) as some_metrics, avg(jvm_usage) as jvm_usage_avg, jvm_usage from metric_indics;
