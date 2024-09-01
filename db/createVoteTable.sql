create table if not exists topic (
	id serial primary key,
	name varchar(50) Not null
);

create table if not exists record(
	id serial primary key,
	voter varchar(50) not null,
	topic_id integer references topic(seqno) not null
);

-----store procedure
create or replace procedure sp_delete_topic_record( target_topic_id integer)
Language plpgsql 
AS $$
declare
	topic_num integer;
	record_num integer;
begin 
	--交易
	begin 
		--查找topic資料
		select count(*) 
		into topic_num
		from topic 
		where topic.id = target_topic_id;
		raise notice '查找topic';

		if topic_num > 0 Then
		
			--查找record資料
			select count(*)
			into record_num
			from record 
			where record.topic_id = target_topic_id;
			raise notice '查找record';

			if record_num > 0 Then
				--刪除record資料
				delete from record
				where record.topic_id = target_topic_id;
				raise notice '刪除record';
				
			end if;
			
			--刪除topic資料
			delete from topic 
			where topic.id = target_topic_id;
			raise notice '刪除topic';
			
		end if;
	exception
		when others then 
		RAISE NOTICE '交易失敗: %', SQLERRM;
	end;	
END$$
