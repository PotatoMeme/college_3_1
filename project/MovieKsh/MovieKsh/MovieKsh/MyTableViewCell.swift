//
//  MyTableViewCell.swift
//  MovieKsh
//
//  Created by comsoft on 2022/05/25.
//

import UIKit

class  MyTableViewCell: UITableViewCell {

    @IBOutlet weak var movieName: UILabel!
    @IBOutlet weak var audiCount: UILabel!
    @IBOutlet weak var audiAccumulate: UILabel!
    @IBOutlet weak var openDt: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
