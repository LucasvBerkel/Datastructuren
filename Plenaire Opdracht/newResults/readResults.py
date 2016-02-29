#Copyright 2013-2014, John McNamara, jmcnamara@cpan.org
import xlsxwriter
import numpy as np

def output(nameFile):

    data = createData(nameFile+".txt")

    # Create an new Excel file and add a worksheet.
    workbook = xlsxwriter.Workbook(nameFile+'.xlsx')
    worksheet = workbook.add_worksheet()
    worksheet.write(0, 0, "Filename:")
    worksheet.write(0, 1, "Correct")
    worksheet.write(0, 2, "Totaal")
    worksheet.write(0, 3, "Tijd")
    for x in range(1, len(data)):
        worksheet.write(x, 0, data[x][0])
        worksheet.write(x, 1, data[x][1])
        worksheet.write(x, 2, data[x][2])
        data[x][3] = int(data[x][3])/1000000000
        worksheet.write(x, 3, data[x][3])

    workbook.close()

def createData(nameFile):
    tempList = ["Naam","Correct","Totaal","Tijd"]
    data = np.array(tempList)
    counter = 0
    with open(nameFile) as data_file:
        for line in data_file:
            splitLine = line.split("\r\n")
            splitLine = splitLine[0].split(" ")
            if(splitLine[0] == "Filename:"):
                tempList[0] = splitLine[1]
            elif(splitLine[0] == "Correct"):
                correctTotal = splitLine[2].split("/")
                tempList[1] = correctTotal[0]
                tempList[2] = correctTotal[1]
            elif(splitLine[0] == "Nanoseconds:"):
                tempList[3] = splitLine[1]
                data = np.vstack([data, tempList])
    return data


if __name__ == "__main__":
    output("trieResults")