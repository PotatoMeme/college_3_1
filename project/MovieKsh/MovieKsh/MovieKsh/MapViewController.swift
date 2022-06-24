//
//  MapViewController.swift
//  MovieKsh
//
//  Created by comsoft on 2022/06/03.
//

import UIKit
import WebKit
class MapViewController: UIViewController {

    @IBOutlet weak var webView: WKWebView!
    override func viewDidLoad() {
        super.viewDidLoad()
        let urlKorString = "https://m.map.naver.com/search2/search.naver?query=%EC%98%81%ED%99%94%EA%B4%80&sm=hty&style=v5"
        //let urlString = urlKorString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        guard let url = URL(string: urlKorString) else{
            return
        }
        let request = URLRequest(url: url)
        webView.load(request)
        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
