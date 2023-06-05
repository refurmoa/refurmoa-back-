package com.highfive.refurmoa.entity;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.highfive.refurmoa.post.dto.request.PostWriteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num", nullable = false)
    private int boardNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "sell_type", nullable = false)
    private int sellType;

    @Column(name = "direct_price")
    private Integer directPrice;

    @Column(name = "auction_price")
    private Integer auctionPrice;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "cur_price", nullable = false)
    private int curPrice;

    @Column(name = "as_date", nullable = false)
    private int asDate;

    @Column(name = "delivery_price", nullable = false)
    private int deliveryPrice;

    @Column(name = "detail_image", nullable = false, length = 100)
    private String detailImage;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "read_count", nullable = false)
    @ColumnDefault(value = "0")
    private int readCount;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "delete_check", nullable = false)
    @ColumnDefault(value = "false")
    private boolean deleteCheck;
    
    public Board(Product prod,PostWriteDTO dto,String detail) {
    	this.asDate = dto.getAs_date() ;
    	this.product =prod;
    	this.deliveryPrice = dto.getDel_price() ;
    	this.sellType = dto.getSell_type() ;
    	this.detailImage=detail;
    	if( dto.getSell_type()==2) {
        	this.directPrice = dto.getDir_price() ;
        	this.curPrice= dto.getDir_price() ;	
    	}
    	else {
    		
    		this.startDate=dto.getStart_date().orElse(null);
        	this.endDate=dto.getEnd_date().orElse(null);
        	this.unitPrice = dto.getUnit_price() ;
        	this.directPrice = dto.getDir_price() ;
        	this.auctionPrice = dto.getAuc_price() ;
        	this.curPrice= dto.getAuc_price() ;	
    	}
	
    }

}
