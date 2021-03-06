//
//  ViewController.swift
//  MovieKsh
//
//  Created by comsoft on 2022/05/25.
//

import UIKit

struct MovieData : Codable {
    let boxOfficeResult : BoxOfficeResult
}

struct BoxOfficeResult : Codable {
    let dailyBoxOfficeList : [DailyBoxOfficeList]
}

struct DailyBoxOfficeList : Codable {
    let movieNm : String
    let audiCnt : String
    let audiAcc : String
    let openDt : String
}

let name = ["aaa","bbb","ccc","ddd","eee"]
class ViewController: UIViewController ,UITableViewDelegate,UITableViewDataSource{
    
    
    
    var segIndex = 0
    @IBAction func switchSeg(_ sender: UISegmentedControl) {
        segIndex = sender.selectedSegmentIndex
    }
    var movieData : MovieData?
    var movieURL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=807ebb0a62facdf7a6e1991bf46ae469&targetDt="//20220524
    
    @IBOutlet weak var table: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        table.delegate = self
        table.dataSource = self
        movieURL += makeYesterdayString()
        getData()
    }
    
    func makeYesterdayString() -> String {
        let y = Calendar.current.date(byAdding: .day,value: -1, to: Date())!
        let dateF = DateFormatter()
        dateF.dateFormat = "yyyyMMdd"
        let day = dateF.string(from: y)
        return day
    }
    func getData() {
        if let url = URL(string: movieURL){
            let session = URLSession(configuration: .default)
            let task = session.dataTask(with: url) { (data, response, error) in
                if error != nil{
                    print(error!)
                    return
                }
                if let JSONdata = data{
                    print(JSONdata,response!)
                    let dataString = String(data:JSONdata,encoding: .utf8)
                    print(dataString!)
                    let decoder = JSONDecoder()
                    do{
                        let decodedData = try decoder.decode(MovieData.self, from: JSONdata)
                        print(decodedData.boxOfficeResult.dailyBoxOfficeList[0].movieNm)
                        print(decodedData.boxOfficeResult.dailyBoxOfficeList[0].audiCnt)
                        self.movieData = decodedData
                        DispatchQueue.main.async {
                            self.table.reloadData()
                        }
                    } catch {
                        print(error)
                    }
                    
                }
            }
            task.resume()
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "myCell", for: indexPath) as! MyTableViewCell
        cell.movieName.text = movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].movieNm
        cell.audiAccumulate.text = movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].audiCnt
        cell.audiCount.text = movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].audiAcc
        cell.openDt.text = movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].openDt
        
        if let aCnt = movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].audiCnt {
            let numF = NumberFormatter()
            numF.numberStyle = .decimal
            let aCount = Int(aCnt)!
            let result = numF.string(for: aCount)!+"???"
            cell.audiCount.text = "??????:\(result)"
            //cell.audiCount.text = "??????:\(aCnt)???"
        }
        if let aAcc =
            movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].audiCnt {
            cell.audiAccumulate.text = "??????:\(aAcc)???"
            
        }
        
        if let oDt =
            movieData?.boxOfficeResult.dailyBoxOfficeList[indexPath.row].openDt {
            cell.openDt.text = "??????:\(oDt)"
            
        }
        print(indexPath.description,separator:" ",terminator: " ")
        return cell
        
    }
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "???????????????(???????????????????????????:"+makeYesterdayString()+")"
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print(indexPath.description)
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let dest = segue.destination as? DetailViewController else{
            return
        }
        let myIndexPath = table.indexPathForSelectedRow!
        let row = myIndexPath.row
        print(row)
        dest.movieName = (movieData?.boxOfficeResult.dailyBoxOfficeList[row].movieNm)!
        dest.seg = segIndex
    }
    
}

