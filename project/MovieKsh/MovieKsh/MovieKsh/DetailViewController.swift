//
//  DetailViewController.swift
//  MovieKsh
//
//  Created by comsoft on 2022/06/03.
//

import UIKit
import WebKit

class DetailViewController: UIViewController {
    
    @IBOutlet weak var webView: WKWebView!
    var movieName = ""
    var seg = 0
    var urlFormat = ""
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = movieName
        
        if(seg == 0){
            urlFormat = "https://www.youtube.com/results?search_query=%@ 예고편"
            
        } else if(seg == 1){
            urlFormat = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%@"
        } else if(seg == 2){
            urlFormat = "https://www.google.com/search?q=%@"
        } else if(seg == 3){
            urlFormat = "https://namu.wiki/w/%@"
        }
        let urlStringKor = String(format: urlFormat, movieName)
        let urlString = urlStringKor.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        guard let url = URL(string: urlString) else{
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
