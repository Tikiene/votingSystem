--insert into topic(name) values( '電腦');
--insert into topic(name) values('滑鼠')
select * from topic;

--insert into record(voter, topic_seqno) values('Leo', 1);
--insert into record(voter, topic_seqno) values('Sandy ', 1);
--insert into record(voter, topic_seqno) values('Sandy ', 2);
--insert into record(voter, topic_seqno) values('Randy ', 1);
--insert into record(voter, topic_seqno) values('RSY ', 1);
select * from record;

select  topic.* , count(record.*)
from record
inner join topic on topic.id = record.topic_id 
group by topic.id, 
topic.name;

call sp_delete_topic_record(3);