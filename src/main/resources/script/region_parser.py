#-*-coding:utf-8 -*-
# 법정지역정보를 바탕으로 region에 넣을 데이터를 만들어주는 파일

print("데이터 파싱을 시작합니다.")

file = open("data.txt", 'r',)
newFile = open("region.sql", 'wt', encoding='utf-8')
id = 1
while True:
    line = file.readline()
    if not line: break
    region = line.split()

    if len(region) == 3 :
        data = "INSERT INTO `study`.`region` (`id`, `region_depth1`, `region_depth2`, `region_depth3`) "
        data += "VALUES ("+str(id)+", '"+ region[0] + "', '" + region[1] + "', '" + region[2] + "');\n"
        newFile.write(data)
        id = id + 1

newFile.close()
file.close()

print("데이터 파싱을 완료했습니다.")