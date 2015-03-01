(ns clj-uuid.v5-test
  (:require [clojure.test   :refer :all]
            [clj-uuid       :refer :all]))



(deftest check-v5-special-cases
  (testing "v5 special case correctness..."
    (is (=
          (v5 +null+ "")
          #uuid "E129F27C-5103-5C5C-844B-CDF0A15E160D"))
    (is (=
          (v5 +namespace-x500+ "")
          #uuid "B4BDF874-8C03-5BD8-8FD7-5E409DFD82C0"))
    (is (=
          (v5 +namespace-oid+ "")
          #uuid "0A68EB57-C88A-5F34-9E9D-27F85E68AF4F"))
    (is (=
          (v5 +namespace-dns+ "")
          #uuid "4EBD0208-8328-5D69-8C44-EC50939C0967"))
    (is (=
          (v5 +namespace-url+ "")
          #uuid "1B4DB7EB-4057-5DDF-91E0-36DEC72071F5"))))

(def +v5-null-ns-cases+
  '((" !\"#$%&'()*+,-./0123456789" #uuid "242E6E8E-7545-5A07-A02F-326EC30CB6B6")
    ("!\"#$%&'()*+,-./0123456789:" #uuid "41F22F8C-6251-523B-B46D-E13DB8EA92AB")
    ("\"#$%&'()*+,-./0123456789:;" #uuid "D8F25381-4007-57D5-85ED-34F8F5C2879B")
    ("#$%&'()*+,-./0123456789:;<" #uuid "45846189-7511-535D-9F94-8E0BD4E32383")
    ("$%&'()*+,-./0123456789:;<=" #uuid "5E0E00BE-97CA-5270-BAE1-826A1528126E")
    ("%&'()*+,-./0123456789:;<=>" #uuid "00715EE8-3D82-5605-B403-CC58B2F4C71A")
    ("&'()*+,-./0123456789:;<=>?" #uuid "15D19A8D-76E1-58D2-B5DE-1E4A5CC3B515")
    ("'()*+,-./0123456789:;<=>?@" #uuid "474DBC21-26B2-5B08-A76B-F06E331B93DD")
    ("()*+,-./0123456789:;<=>?@A" #uuid "65B54688-43AD-54DC-B052-B612E09B8AD6")
    (")*+,-./0123456789:;<=>?@AB" #uuid "20CCA61F-4F55-5420-9CD0-30B1D166FE5B")
    ("*+,-./0123456789:;<=>?@ABC" #uuid "D9C06A76-8189-583B-B3E9-FFC143531914")
    ("+,-./0123456789:;<=>?@ABCD" #uuid "014FAF6F-5246-5007-B5CB-E0EFC331D3B0")
    (",-./0123456789:;<=>?@ABCDE" #uuid "D87E9933-09B4-5DA5-B980-7CC65E6B471B")
    ("-./0123456789:;<=>?@ABCDEF" #uuid "31E0B629-9781-5E59-B7AF-04D2D88660AF")
    ("./0123456789:;<=>?@ABCDEFG" #uuid "CCED497C-35AF-5FE4-A6C2-61A0572FDA59")
    ("/0123456789:;<=>?@ABCDEFGH" #uuid "C993482E-33E9-5CC8-9E51-8EE46B3FE152")
    ("0123456789:;<=>?@ABCDEFGHI" #uuid "0F6BDD65-B698-5762-AD0E-AB35B5D73885")
    ("123456789:;<=>?@ABCDEFGHIJ" #uuid "AA1D4298-9A26-5AB9-B0F2-E1D7AF6CF813")
    ("23456789:;<=>?@ABCDEFGHIJK" #uuid "27F508C3-7402-57F8-B17B-0D81C0D7F062")
    ("3456789:;<=>?@ABCDEFGHIJKL" #uuid "6EC8BBC6-987E-52E3-A948-EE3DBF3D0D4A")
    ("456789:;<=>?@ABCDEFGHIJKLM" #uuid "25DFBD80-01B3-5540-AEBA-22E627A48899")
    ("56789:;<=>?@ABCDEFGHIJKLMN" #uuid "FFF7A94C-F5F2-51FF-9D5B-55B388600BE6")
    ("6789:;<=>?@ABCDEFGHIJKLMNO" #uuid "B4FD5C57-0AE3-5D8F-ADAC-1E8E8CB60C27")
    ("789:;<=>?@ABCDEFGHIJKLMNOP" #uuid "0E1880FB-CB2E-588B-9CFD-6B451E447E50")
    ("89:;<=>?@ABCDEFGHIJKLMNOPQ" #uuid "5040D88C-1881-599D-A954-A2CBCAFFC758")
    ("9:;<=>?@ABCDEFGHIJKLMNOPQR" #uuid "77EEC4CC-5AD9-54AA-91D6-952336D771FE")
    (":;<=>?@ABCDEFGHIJKLMNOPQRS" #uuid "30310096-BD69-5B19-AF68-FFBC854A06F6")
    (";<=>?@ABCDEFGHIJKLMNOPQRST" #uuid "D9A83C73-1B93-5E59-9BF3-7FD6707085B8")
    ("<=>?@ABCDEFGHIJKLMNOPQRSTU" #uuid "5BC33DA2-F6B3-5CF7-822C-1289C81DE1A1")
    ("=>?@ABCDEFGHIJKLMNOPQRSTUV" #uuid "31D4E146-67ED-50C4-BCC6-BB3C74CDDAAC")
    (">?@ABCDEFGHIJKLMNOPQRSTUVW" #uuid "F3939252-7456-5360-B34D-E327B823957F")
    ("?@ABCDEFGHIJKLMNOPQRSTUVWX" #uuid "D518FB94-0083-5933-BEE1-4D21694188F2")
    ("@ABCDEFGHIJKLMNOPQRSTUVWXY" #uuid "6852364E-017E-5D10-B4F9-63FE7DCF6D85")
    ("ABCDEFGHIJKLMNOPQRSTUVWXYZ" #uuid "009442DE-7929-5001-9022-0D340553A9D6")
    ("BCDEFGHIJKLMNOPQRSTUVWXYZ[" #uuid "58860D2D-09C9-5B46-B330-2E0867C7EB7A")
    ("CDEFGHIJKLMNOPQRSTUVWXYZ[\\" #uuid "3D6024D9-75E7-5BBF-90DB-097939462E63")
    ("DEFGHIJKLMNOPQRSTUVWXYZ[\\]" #uuid "F400AE6B-B384-5DD3-8D3C-BCEC42E64F9F")
    ("EFGHIJKLMNOPQRSTUVWXYZ[\\]^" #uuid "7C466264-9005-558E-8F8B-844868A27041")
    ("FGHIJKLMNOPQRSTUVWXYZ[\\]^_" #uuid "96F0E71A-AEC5-5F2D-82BD-A38CB763589B")
    ("GHIJKLMNOPQRSTUVWXYZ[\\]^_`" #uuid "B2133431-BC91-5C9B-868D-7F182215CF59")
    ("HIJKLMNOPQRSTUVWXYZ[\\]^_`a" #uuid "70572482-055E-5013-8755-EF77D56E31CF")
    ("IJKLMNOPQRSTUVWXYZ[\\]^_`ab" #uuid "F4A88F92-5D12-5495-A4A3-0933441B535A")
    ("JKLMNOPQRSTUVWXYZ[\\]^_`abc" #uuid "F5FC6436-BB13-5C2E-8BBC-349F5BBCF126")
    ("KLMNOPQRSTUVWXYZ[\\]^_`abcd" #uuid "C77E9DA8-0B0C-5DBE-8B1C-85E7BA4C631E")
    ("LMNOPQRSTUVWXYZ[\\]^_`abcde" #uuid "2C3EF189-369C-5FE6-ABE8-D071246957B6")
    ("MNOPQRSTUVWXYZ[\\]^_`abcdef" #uuid "BF42EC1C-0234-5226-9618-9538F89A98FF")
    ("NOPQRSTUVWXYZ[\\]^_`abcdefg" #uuid "39056E38-A0B0-5194-B16E-D6F75AFA93AE")
    ("OPQRSTUVWXYZ[\\]^_`abcdefgh" #uuid "17C8A690-3261-5EA5-9DE1-FF05B956E56B")
    ("PQRSTUVWXYZ[\\]^_`abcdefghi" #uuid "763D44CE-E4EF-5B63-A5EC-6BD6ACB860B8")
    ("QRSTUVWXYZ[\\]^_`abcdefghij" #uuid "880D1D2E-3912-51C8-A7EF-A2954B96D6D8")
    ("RSTUVWXYZ[\\]^_`abcdefghijk" #uuid "DDB08918-D45E-52D2-9999-B4C5925C67B7")
    ("STUVWXYZ[\\]^_`abcdefghijkl" #uuid "C9770255-BAFC-5974-9F23-B17C119F7FF3")
    ("TUVWXYZ[\\]^_`abcdefghijklm" #uuid "58128319-128B-54FF-B202-33A2A271B380")
    ("UVWXYZ[\\]^_`abcdefghijklmn" #uuid "7D1628F8-615E-593E-B56C-DDC5BCDDC45C")
    ("VWXYZ[\\]^_`abcdefghijklmno" #uuid "1359497D-5D7F-5288-A021-88F071979ACC")
    ("WXYZ[\\]^_`abcdefghijklmnop" #uuid "097BB556-5D10-5915-AB34-136DFBEDA005")
    ("XYZ[\\]^_`abcdefghijklmnopq" #uuid "85AB2C59-868D-52B2-8D28-A04ADF32C456")
    ("YZ[\\]^_`abcdefghijklmnopqr" #uuid "430C7305-D1F8-592D-BCB8-7345F0577B51")
    ("Z[\\]^_`abcdefghijklmnopqrs" #uuid "AD411859-3F9D-5CEF-8891-2D91453BF02B")
    ("[\\]^_`abcdefghijklmnopqrst" #uuid "AE01E30C-B5DF-5A40-AD98-C2E2E5F00505")
    ("\\]^_`abcdefghijklmnopqrstu" #uuid "D306C4AD-7215-5331-895C-D3B123AB07F8")
    ("]^_`abcdefghijklmnopqrstuv" #uuid "9670F138-C67F-5A3B-8421-3DB6D3E1E271")
    ("^_`abcdefghijklmnopqrstuvw" #uuid "E246E515-EE87-5E71-9766-FE5CC691F2B6")
    ("_`abcdefghijklmnopqrstuvwx" #uuid "CA8B9A8A-C953-58C2-8CBC-B9D5C08B7082")
    ("`abcdefghijklmnopqrstuvwxy" #uuid "72A55C81-7771-573C-A968-D9C2E99349F6")
    ("abcdefghijklmnopqrstuvwxyz" #uuid "15EF97D2-2E8B-5705-B040-13073A8403C6")
    ("bcdefghijklmnopqrstuvwxyz{" #uuid "2FBC6E63-876F-5B40-9740-0194EAFF582A")
    ("cdefghijklmnopqrstuvwxyz{|" #uuid "DA8F3757-7272-5E61-AB62-AA925CA23FEE")
    ("defghijklmnopqrstuvwxyz{|}" #uuid "0D3D2034-3451-5F68-90AD-1C6C278DEC4D")
    ("efghijklmnopqrstuvwxyz{|}~" #uuid "5E81B26E-6EF6-57ED-9BD7-21B6BC153AED")
    ("fghijklmnopqrstuvwxyz{|}~ " #uuid "083EE855-E5E0-5ED3-8E76-93BA065FF370")
    ("ghijklmnopqrstuvwxyz{|}~ !" #uuid "8B2746D3-EDCB-5AC5-9A06-6DA96E4D135C")
    ("hijklmnopqrstuvwxyz{|}~ !\"" #uuid "EC9932CD-6FBA-53C9-B3F5-6D41312DA6A3")
    ("ijklmnopqrstuvwxyz{|}~ !\"#" #uuid "2D587B17-4476-5EFF-BF0E-1B3D567DC402")
    ("jklmnopqrstuvwxyz{|}~ !\"#$" #uuid "D1682F44-2F08-58D6-BD7D-210C91B87111")
    ("klmnopqrstuvwxyz{|}~ !\"#$%" #uuid "1EE16A30-3B2B-5AB7-8E1D-EFF3A5E4C7E8")
    ("lmnopqrstuvwxyz{|}~ !\"#$%&" #uuid "CD66FFE0-37D2-55F6-8049-81F3AD4A881E")
    ("mnopqrstuvwxyz{|}~ !\"#$%&'" #uuid "63638AF4-3E7C-5A7D-82E7-DC7FE152C0B7")
    ("nopqrstuvwxyz{|}~ !\"#$%&'(" #uuid "1CBCA560-CE4A-52F6-B86C-F25CA1EAB1EC")
    ("opqrstuvwxyz{|}~ !\"#$%&'()" #uuid "ED541D15-90DE-5790-AEAC-AE92696FE339")
    ("pqrstuvwxyz{|}~ !\"#$%&'()*" #uuid "4DB93134-5B4E-537C-A178-3CE6E2EAB2F6")
    ("qrstuvwxyz{|}~ !\"#$%&'()*+" #uuid "6402FA45-D31A-5E79-8CDA-F0B5F5BD88DD")
    ("rstuvwxyz{|}~ !\"#$%&'()*+," #uuid "063A63DF-A01A-5A67-9BB9-B3BF4E42131B")
    ("stuvwxyz{|}~ !\"#$%&'()*+,-" #uuid "61DD21C6-2DB9-5C41-9602-577CCF4B7C8B")
    ("tuvwxyz{|}~ !\"#$%&'()*+,-." #uuid "6799784F-3507-5450-9274-D9BA10AFC752")
    ("uvwxyz{|}~ !\"#$%&'()*+,-./" #uuid "30A6284C-6269-5D2E-98E3-7A87D84F51F1")
    ("vwxyz{|}~ !\"#$%&'()*+,-./0" #uuid "18777242-8DF3-53AE-B695-FDCAF7E1E458")
    ("wxyz{|}~ !\"#$%&'()*+,-./01" #uuid "ED8BA679-1BC9-5EB4-9EED-DD717809FEC2")
    ("xyz{|}~ !\"#$%&'()*+,-./012" #uuid "CE96C5BE-70EE-5594-9A5B-8C96A456A6D9")
    ("yz{|}~ !\"#$%&'()*+,-./0123" #uuid "5C884C16-E0B7-5A26-A32A-6DB49B501A1A")
    ("z{|}~ !\"#$%&'()*+,-./01234" #uuid "7C0EBA71-82F0-5904-B745-75541AE65312")
    ("{|}~ !\"#$%&'()*+,-./012345" #uuid "37CDD9D8-A94F-5BB5-AA57-97A92ACA22FC")
    ("|}~ !\"#$%&'()*+,-./0123456" #uuid "BB23D8C2-29F0-5EC5-BF50-B7092FA62204")
    ("}~ !\"#$%&'()*+,-./01234567" #uuid "AD3AD027-A2ED-5F09-B581-78AD87D86A7C")
    ("~ !\"#$%&'()*+,-./012345678" #uuid "093B7461-98EF-55DC-8616-890210247499")
    ))


(deftest check-v5-null-ns-cases
  (testing "v5 null-ns case-based correctness..."
    (doseq [case +v5-null-ns-cases+]
      (is (= (second case) (v5 +null+ (first case)))))))



(def +v5-dns-ns-cases+
  '((" !\"#$%&'()*+,-./0123456789" #uuid "20936B0A-AB47-5EBD-9788-7F379130910E")
    ("!\"#$%&'()*+,-./0123456789:" #uuid "06454276-F2D4-5F4C-9A62-E9471E2D1EA4")
    ("\"#$%&'()*+,-./0123456789:;" #uuid "AD979D13-7728-5FB1-8799-52D2B973DC7E")
    ("#$%&'()*+,-./0123456789:;<" #uuid "E0A54D87-993D-510D-BB56-6660B266330F")
    ("$%&'()*+,-./0123456789:;<=" #uuid "7F9C48C3-4145-5EDE-BE28-03EDD13C2FAB")
    ("%&'()*+,-./0123456789:;<=>" #uuid "A9AAE61F-D1A3-5F61-BE26-FED829E8AB6D")
    ("&'()*+,-./0123456789:;<=>?" #uuid "CF2DBC66-F8A6-50ED-B00A-9A6A6206CA8D")
    ("'()*+,-./0123456789:;<=>?@" #uuid "84107B8C-C5EB-54B4-9ACE-B607F24BBB0F")
    ("()*+,-./0123456789:;<=>?@A" #uuid "1D5E2031-445A-53F7-B088-4E20C41249ED")
    (")*+,-./0123456789:;<=>?@AB" #uuid "CAB0515F-1641-56BA-9042-967CC0243D7E")
    ("*+,-./0123456789:;<=>?@ABC" #uuid "7548EB9D-7A15-5C6A-BE64-D51BD5FD9EC8")
    ("+,-./0123456789:;<=>?@ABCD" #uuid "73DF2DFA-3F14-557A-84FB-3C3011EC902E")
    (",-./0123456789:;<=>?@ABCDE" #uuid "49DEDE7F-121B-5697-B478-16EBC77A2C1F")
    ("-./0123456789:;<=>?@ABCDEF" #uuid "D4089686-20D4-5A75-9058-95C3289C9A91")
    ("./0123456789:;<=>?@ABCDEFG" #uuid "E6312674-9157-5749-9A6E-760667886FD6")
    ("/0123456789:;<=>?@ABCDEFGH" #uuid "E4C7BB57-9085-56DA-ADCA-4A742650EEDA")
    ("0123456789:;<=>?@ABCDEFGHI" #uuid "C4863E91-D99C-5BFE-B44B-5E0D58EBE9B1")
    ("123456789:;<=>?@ABCDEFGHIJ" #uuid "7A635616-E871-5962-821C-7F7FDA74627A")
    ("23456789:;<=>?@ABCDEFGHIJK" #uuid "3F9F12F2-520F-522A-801C-C0EFA4078A1C")
    ("3456789:;<=>?@ABCDEFGHIJKL" #uuid "820886F4-5CA7-595B-B484-FF40FC94D5CD")
    ("456789:;<=>?@ABCDEFGHIJKLM" #uuid "436BA120-F191-54FE-9532-176DDBFFBA20")
    ("56789:;<=>?@ABCDEFGHIJKLMN" #uuid "4054BBB1-FD3A-5D38-BA77-C6F5620904D9")
    ("6789:;<=>?@ABCDEFGHIJKLMNO" #uuid "F9E7C39E-802E-53B3-B7CE-F84C02BDB3A8")
    ("789:;<=>?@ABCDEFGHIJKLMNOP" #uuid "E620E967-540D-5D2A-A361-6E7F5B4DB83B")
    ("89:;<=>?@ABCDEFGHIJKLMNOPQ" #uuid "2FCD4A7A-E98E-5D97-90CC-03DB0575EF80")
    ("9:;<=>?@ABCDEFGHIJKLMNOPQR" #uuid "2442A8C4-DC2A-5A15-A65F-E3101E98E5CC")
    (":;<=>?@ABCDEFGHIJKLMNOPQRS" #uuid "7C9CD2DF-338A-5F77-B9C2-4346854E55AF")
    (";<=>?@ABCDEFGHIJKLMNOPQRST" #uuid "C8B122B0-C58A-5F38-84F7-4D5CDCE21CAA")
    ("<=>?@ABCDEFGHIJKLMNOPQRSTU" #uuid "59176B9E-7ADC-5CC3-BC48-033A4D5DF013")
    ("=>?@ABCDEFGHIJKLMNOPQRSTUV" #uuid "8BC00B22-EEF0-57A3-85D1-4ED9B3B28B8C")
    (">?@ABCDEFGHIJKLMNOPQRSTUVW" #uuid "6E712F40-0361-53E0-9045-AD363F9C4333")
    ("?@ABCDEFGHIJKLMNOPQRSTUVWX" #uuid "55AC4EE7-4E09-5257-9847-7F2DBE57C45B")
    ("@ABCDEFGHIJKLMNOPQRSTUVWXY" #uuid "430C2AE3-8037-5CD7-A6C6-66A4E8AFC2AF")
    ("ABCDEFGHIJKLMNOPQRSTUVWXYZ" #uuid "8C31F75B-F02A-555C-9235-21C2CF0BA6FF")
    ("BCDEFGHIJKLMNOPQRSTUVWXYZ[" #uuid "360A5E98-3C9D-5D1E-ACC9-65B7CE3CE70D")
    ("CDEFGHIJKLMNOPQRSTUVWXYZ[\\" #uuid "4BC8DE4D-B04D-5D09-B6C3-93E69D774A34")
    ("DEFGHIJKLMNOPQRSTUVWXYZ[\\]" #uuid "95990338-4A46-5875-AA9F-127F3F78B97E")
    ("EFGHIJKLMNOPQRSTUVWXYZ[\\]^" #uuid "5079A2EE-3786-5E24-8253-3A045B856228")
    ("FGHIJKLMNOPQRSTUVWXYZ[\\]^_" #uuid "C9C5363D-6470-5A55-8A58-6B3ADA8F61AC")
    ("GHIJKLMNOPQRSTUVWXYZ[\\]^_`" #uuid "A0D07537-22DC-5DC1-A1FE-35F892AA7A9A")
    ("HIJKLMNOPQRSTUVWXYZ[\\]^_`a" #uuid "A8FE82DA-8CE2-5266-970D-9D593A59606C")
    ("IJKLMNOPQRSTUVWXYZ[\\]^_`ab" #uuid "05C970D7-9C81-5CE7-87C3-27008C8862D1")
    ("JKLMNOPQRSTUVWXYZ[\\]^_`abc" #uuid "3F32768E-31FF-5E45-9DF4-61F4961144E0")
    ("KLMNOPQRSTUVWXYZ[\\]^_`abcd" #uuid "927713A3-5D2C-562E-B288-B78ABDFAC527")
    ("LMNOPQRSTUVWXYZ[\\]^_`abcde" #uuid "7FBF943F-6E9D-5B07-A2DA-B8A68FB256F7")
    ("MNOPQRSTUVWXYZ[\\]^_`abcdef" #uuid "19D04CF8-537E-562C-8ED5-F9568C444F33")
    ("NOPQRSTUVWXYZ[\\]^_`abcdefg" #uuid "2EAB5AE8-B9E2-5F3D-B66B-D45C929B4BBC")
    ("OPQRSTUVWXYZ[\\]^_`abcdefgh" #uuid "4A489F5C-1E9F-5441-B49B-8018442346CC")
    ("PQRSTUVWXYZ[\\]^_`abcdefghi" #uuid "B8DA5FD0-F067-57D0-A3CB-7BC7168BFDAB")
    ("QRSTUVWXYZ[\\]^_`abcdefghij" #uuid "BF6C0099-7DBA-5198-88BF-B2A8E540A3CE")
    ("RSTUVWXYZ[\\]^_`abcdefghijk" #uuid "49F92AD5-E5BD-5DE2-B1C2-167774882BC0")
    ("STUVWXYZ[\\]^_`abcdefghijkl" #uuid "2862D585-D6E6-5994-98C7-736E5A0B8D77")
    ("TUVWXYZ[\\]^_`abcdefghijklm" #uuid "BA036CCB-4051-5ED7-9602-76134092DAA5")
    ("UVWXYZ[\\]^_`abcdefghijklmn" #uuid "AB6C1DE0-5BD7-53E3-B507-2F19972F8CA7")
    ("VWXYZ[\\]^_`abcdefghijklmno" #uuid "3962BFC1-ADF7-57EF-AA6F-ECD29F1BE434")
    ("WXYZ[\\]^_`abcdefghijklmnop" #uuid "93515E40-497D-583D-A4EC-2BE08089533A")
    ("XYZ[\\]^_`abcdefghijklmnopq" #uuid "194AD9B4-04CF-51A3-8D2D-3AEAED62B84D")
    ("YZ[\\]^_`abcdefghijklmnopqr" #uuid "C19D95E9-08C0-5E8D-93C3-FEE78F781408")
    ("Z[\\]^_`abcdefghijklmnopqrs" #uuid "462415ED-8D55-5894-AEE0-A3F38A985FB1")
    ("[\\]^_`abcdefghijklmnopqrst" #uuid "F0EF3EAA-3B12-5AE7-87E1-F2BA988B59CF")
    ("\\]^_`abcdefghijklmnopqrstu" #uuid "859212C2-6DE4-5593-8986-5163D3D7851D")
    ("]^_`abcdefghijklmnopqrstuv" #uuid "BFCFB5EC-0CE3-58FC-9BB3-1B3EB3B4FD98")
    ("^_`abcdefghijklmnopqrstuvw" #uuid "01724DA7-DCAE-569B-8D14-D81F9A37295E")
    ("_`abcdefghijklmnopqrstuvwx" #uuid "52443FBF-2C97-5DB2-BF13-AE9526B617D1")
    ("`abcdefghijklmnopqrstuvwxy" #uuid "FF0EC0B6-CECB-517F-87A0-194759C2DFC4")
    ("abcdefghijklmnopqrstuvwxyz" #uuid "E1CDA567-F00A-578F-B4EC-F248BD2743B2")
    ("bcdefghijklmnopqrstuvwxyz{" #uuid "1B9A7485-0653-5110-8557-7EB635C0FD62")
    ("cdefghijklmnopqrstuvwxyz{|" #uuid "EFFF805A-741C-5B3E-A5BC-5E0FCBBAC60A")
    ("defghijklmnopqrstuvwxyz{|}" #uuid "A1F0CDFB-6C7F-5F8F-BBFC-8ACAB78F287F")
    ("efghijklmnopqrstuvwxyz{|}~" #uuid "2153125C-E5C1-5805-A4A5-51923A40D57B")
    ("fghijklmnopqrstuvwxyz{|}~ " #uuid "AE92DD4A-6B17-591C-A404-33548F8E0D08")
    ("ghijklmnopqrstuvwxyz{|}~ !" #uuid "7D9703EA-F876-51AF-869A-ED3485EF40FA")
    ("hijklmnopqrstuvwxyz{|}~ !\"" #uuid "00AA4363-CDAA-5A3F-8F00-C685EDFE1455")
    ("ijklmnopqrstuvwxyz{|}~ !\"#" #uuid "19E4C188-E0B5-5770-B878-86227B76B26E")
    ("jklmnopqrstuvwxyz{|}~ !\"#$" #uuid "E539E727-3605-5DEC-96CF-2F4FC633F57D")
    ("klmnopqrstuvwxyz{|}~ !\"#$%" #uuid "EA16CBB8-B495-5113-8DC1-11ACBA333C2C")
    ("lmnopqrstuvwxyz{|}~ !\"#$%&" #uuid "A2A8234B-874E-5C87-846A-0558FCF8C898")
    ("mnopqrstuvwxyz{|}~ !\"#$%&'" #uuid "E9B76262-6370-5C36-BD09-627F0EC78E7E")
    ("nopqrstuvwxyz{|}~ !\"#$%&'(" #uuid "6505BF7B-C096-5DDE-83D1-68BE3D450CC8")
    ("opqrstuvwxyz{|}~ !\"#$%&'()" #uuid "2DAD12B6-17EA-5556-AEDF-B34FC0418BBD")
    ("pqrstuvwxyz{|}~ !\"#$%&'()*" #uuid "797F9A21-4F7C-556C-8BB8-95337F4F47EF")
    ("qrstuvwxyz{|}~ !\"#$%&'()*+" #uuid "D3674C30-0DA6-5F4D-9F36-C19C07E5B707")
    ("rstuvwxyz{|}~ !\"#$%&'()*+," #uuid "1F7BB665-4D61-59CF-8D4D-F3E203C99464")
    ("stuvwxyz{|}~ !\"#$%&'()*+,-" #uuid "C3DC70CC-6B5E-53FE-B0D6-54131AD96063")
    ("tuvwxyz{|}~ !\"#$%&'()*+,-." #uuid "DE2F3C19-9075-5873-873C-94965089CFA7")
    ("uvwxyz{|}~ !\"#$%&'()*+,-./" #uuid "1308A9AE-0A07-5DBD-8F78-EB5C6C1BB7DE")
    ("vwxyz{|}~ !\"#$%&'()*+,-./0" #uuid "90A57D98-EB6A-52E9-92AA-6F1377741632")
    ("wxyz{|}~ !\"#$%&'()*+,-./01" #uuid "A9216C3B-DFDD-561C-96B0-60EC57DF2176")
    ("xyz{|}~ !\"#$%&'()*+,-./012" #uuid "7DCDFFF0-A0E0-5CE5-989D-61A73D67C4E8")
    ("yz{|}~ !\"#$%&'()*+,-./0123" #uuid "7EA67B17-A917-5E1B-8807-818DC9B4A9E6")
    ("z{|}~ !\"#$%&'()*+,-./01234" #uuid "E7BC2E14-9029-5BBF-8367-10437778EE67")
    ("{|}~ !\"#$%&'()*+,-./012345" #uuid "2EB1112A-6E8D-55A7-B712-93B109E53CAF")
    ("|}~ !\"#$%&'()*+,-./0123456" #uuid "5783E9F5-4723-503F-8800-9BF782817DC4")
    ("}~ !\"#$%&'()*+,-./01234567" #uuid "CA765530-6B64-5791-AFD7-8138A5D1AB00")
    ("~ !\"#$%&'()*+,-./012345678" #uuid "59B8A01C-BE09-50A6-802F-C93B1D6A5C8E")
    ))


(deftest check-v5-dns-ns-cases
  (testing "v5 dns-ns case-based correctness..."
    (doseq [case +v5-dns-ns-cases+]
      (is (= (second case) (v5 +namespace-dns+ (first case)))))))


(def +v5-oid-ns-cases+
  '((" !\"#$%&'()*+,-./0123456789" #uuid "71D9EA92-356D-5940-B0A9-951BD32DDBAE")
    ("!\"#$%&'()*+,-./0123456789:" #uuid "F3DDBCEB-D3AB-5B85-83E4-8B89B504FBED")
    ("\"#$%&'()*+,-./0123456789:;" #uuid "C0C2E013-BE36-558E-9E9B-6D19BDEC4147")
    ("#$%&'()*+,-./0123456789:;<" #uuid "8AE9B168-73AC-5788-AA22-7002D8D39921")
    ("$%&'()*+,-./0123456789:;<=" #uuid "28A1EA4F-2FEE-5A36-9460-6DAEDB3FF36B")
    ("%&'()*+,-./0123456789:;<=>" #uuid "C26A66CB-E2C0-59AE-A65A-A027DA13677D")
    ("&'()*+,-./0123456789:;<=>?" #uuid "AE2B437A-0DA2-5988-883B-BEF432C63A43")
    ("'()*+,-./0123456789:;<=>?@" #uuid "49AF28B5-F7F7-5720-840D-94A052236422")
    ("()*+,-./0123456789:;<=>?@A" #uuid "390456C5-FB1B-52CB-B922-D9EE75F94F80")
    (")*+,-./0123456789:;<=>?@AB" #uuid "104297AF-C263-5E38-8792-C2CE39168482")
    ("*+,-./0123456789:;<=>?@ABC" #uuid "E6AA9B91-409E-58E1-8BFC-B3A9907F2749")
    ("+,-./0123456789:;<=>?@ABCD" #uuid "B4CEB85C-FEB9-564A-AE1D-90273F89CC2B")
    (",-./0123456789:;<=>?@ABCDE" #uuid "F245626A-71F8-5F73-A3B7-5DC122E0A768")
    ("-./0123456789:;<=>?@ABCDEF" #uuid "85B90BEE-EE31-53BD-804B-61014E55DDE8")
    ("./0123456789:;<=>?@ABCDEFG" #uuid "51A2EE70-605E-540D-819C-8F0231764259")
    ("/0123456789:;<=>?@ABCDEFGH" #uuid "3A31EB3A-DE8C-520D-ADC9-2048C2B9123F")
    ("0123456789:;<=>?@ABCDEFGHI" #uuid "4EB6C362-7709-5F58-AF4B-69BFB0D4D3E6")
    ("123456789:;<=>?@ABCDEFGHIJ" #uuid "8EA7FFEC-7357-5073-9E90-9B6A6091BE38")
    ("23456789:;<=>?@ABCDEFGHIJK" #uuid "4FEF89F0-0921-5118-A456-EFA5EB9FCFFB")
    ("3456789:;<=>?@ABCDEFGHIJKL" #uuid "73C117FF-9E97-5950-8334-6A4777365851")
    ("456789:;<=>?@ABCDEFGHIJKLM" #uuid "F3391045-7646-5944-9710-467598E6E3B4")
    ("56789:;<=>?@ABCDEFGHIJKLMN" #uuid "0CE3D8DB-7C5E-5CA8-85B1-54C4C4B6D755")
    ("6789:;<=>?@ABCDEFGHIJKLMNO" #uuid "8E34E08C-256B-5F08-B7DF-710AF259AE72")
    ("789:;<=>?@ABCDEFGHIJKLMNOP" #uuid "DC721C82-30DC-51B8-890B-BA46CEFDAF22")
    ("89:;<=>?@ABCDEFGHIJKLMNOPQ" #uuid "A303B3EC-12F5-5E20-B9BD-3AEA01124BB4")
    ("9:;<=>?@ABCDEFGHIJKLMNOPQR" #uuid "75FC219A-ABE7-56C2-AD7A-9E91BB0E0504")
    (":;<=>?@ABCDEFGHIJKLMNOPQRS" #uuid "F6183650-657C-5BB0-94CE-F9348D8EF981")
    (";<=>?@ABCDEFGHIJKLMNOPQRST" #uuid "7B0B3E18-8370-55FD-AC8D-189697A6D008")
    ("<=>?@ABCDEFGHIJKLMNOPQRSTU" #uuid "524D3B31-14EC-5906-BB3C-339F40F4B5C6")
    ("=>?@ABCDEFGHIJKLMNOPQRSTUV" #uuid "9B3073B9-2786-566D-892F-8B5D0BB0BE2C")
    (">?@ABCDEFGHIJKLMNOPQRSTUVW" #uuid "CBE55728-B4B1-5C5A-8A51-395B5236B73E")
    ("?@ABCDEFGHIJKLMNOPQRSTUVWX" #uuid "474BDBD0-EEA8-57D8-A0BC-85AC730D2E83")
    ("@ABCDEFGHIJKLMNOPQRSTUVWXY" #uuid "F031E945-A302-50ED-BEA1-625535DF0708")
    ("ABCDEFGHIJKLMNOPQRSTUVWXYZ" #uuid "D4D4B4F7-CA21-5A38-9A1A-B5952F780839")
    ("BCDEFGHIJKLMNOPQRSTUVWXYZ[" #uuid "698B1CC6-4490-5AA6-8685-C4CA9A70B99C")
    ("CDEFGHIJKLMNOPQRSTUVWXYZ[\\" #uuid "4F5CB0D5-5171-5AF3-86DB-C149DDA86CA0")
    ("DEFGHIJKLMNOPQRSTUVWXYZ[\\]" #uuid "B1AEA275-F005-5B74-9545-5AD3AE481DA8")
    ("EFGHIJKLMNOPQRSTUVWXYZ[\\]^" #uuid "3D891BBB-55FE-53EA-AA37-381DBE4B5B8F")
    ("FGHIJKLMNOPQRSTUVWXYZ[\\]^_" #uuid "DD1DE872-0AE7-5A76-80A8-983D3B8B002D")
    ("GHIJKLMNOPQRSTUVWXYZ[\\]^_`" #uuid "EE601373-012B-5CDD-9985-E1C1955AEBB1")
    ("HIJKLMNOPQRSTUVWXYZ[\\]^_`a" #uuid "BE7276B7-7371-5208-AC0D-397C0D89DD84")
    ("IJKLMNOPQRSTUVWXYZ[\\]^_`ab" #uuid "9D584707-CE26-5AEF-9CCC-E462EA568EE3")
    ("JKLMNOPQRSTUVWXYZ[\\]^_`abc" #uuid "7B925C14-44FD-553A-A75F-B97369B97AF4")
    ("KLMNOPQRSTUVWXYZ[\\]^_`abcd" #uuid "C445B204-6E1B-5D47-915F-8651014BD350")
    ("LMNOPQRSTUVWXYZ[\\]^_`abcde" #uuid "DD7C53C8-D447-58DB-85F9-F8C37165F1B6")
    ("MNOPQRSTUVWXYZ[\\]^_`abcdef" #uuid "B565C336-E751-5DCB-ABDD-D44C4D3E5576")
    ("NOPQRSTUVWXYZ[\\]^_`abcdefg" #uuid "D4657BED-4DB4-579C-AF46-91442BEBCD03")
    ("OPQRSTUVWXYZ[\\]^_`abcdefgh" #uuid "ECD09D93-66BB-590A-81A0-CE998E5A1C4E")
    ("PQRSTUVWXYZ[\\]^_`abcdefghi" #uuid "665BBED6-C93C-5706-9A53-97346A5A675C")
    ("QRSTUVWXYZ[\\]^_`abcdefghij" #uuid "9AB63221-3159-59F3-8B50-BCAD5FC4AD22")
    ("RSTUVWXYZ[\\]^_`abcdefghijk" #uuid "41ACDB12-B0CE-5ED6-966C-35C85612106C")
    ("STUVWXYZ[\\]^_`abcdefghijkl" #uuid "6F22A8AB-E584-59CC-9FFA-B662E614B29F")
    ("TUVWXYZ[\\]^_`abcdefghijklm" #uuid "BAC2AFC2-362E-5339-B1EF-D19915309B3E")
    ("UVWXYZ[\\]^_`abcdefghijklmn" #uuid "1C3CD630-FD8B-5D26-95C8-8C84B2314C63")
    ("VWXYZ[\\]^_`abcdefghijklmno" #uuid "2453EBB1-5045-5C90-88B6-7B49AFD689F8")
    ("WXYZ[\\]^_`abcdefghijklmnop" #uuid "DA7E4BB1-8A1B-5E4C-B394-AE369DCABCAF")
    ("XYZ[\\]^_`abcdefghijklmnopq" #uuid "A2398C09-E200-502F-B0A3-EA9FA95D13E6")
    ("YZ[\\]^_`abcdefghijklmnopqr" #uuid "9E505244-5804-5938-88C8-8FAF526F39BF")
    ("Z[\\]^_`abcdefghijklmnopqrs" #uuid "D53E3FA0-EB27-56E9-9FAD-6D62EFEB433C")
    ("[\\]^_`abcdefghijklmnopqrst" #uuid "E9BFA441-02D4-54B0-AC02-810B5EFD6D6C")
    ("\\]^_`abcdefghijklmnopqrstu" #uuid "F31AF79E-228C-5194-B845-511FC0E806EA")
    ("]^_`abcdefghijklmnopqrstuv" #uuid "45FF0AFD-5D35-5C0D-A7E7-51BD79186BA8")
    ("^_`abcdefghijklmnopqrstuvw" #uuid "D0D588D4-7C0D-54B4-8286-7C7B15D62013")
    ("_`abcdefghijklmnopqrstuvwx" #uuid "D9593422-69B8-590C-B207-F35C0974D60B")
    ("`abcdefghijklmnopqrstuvwxy" #uuid "294448B1-C82C-5FF0-BB48-E97D4F9C88AF")
    ("abcdefghijklmnopqrstuvwxyz" #uuid "6C7D893E-A11C-5274-B839-CA68CD47E627")
    ("bcdefghijklmnopqrstuvwxyz{" #uuid "23F2DC57-D808-5892-90FD-C7453C8AF511")
    ("cdefghijklmnopqrstuvwxyz{|" #uuid "43C0C792-0CDC-5D2E-A332-21243A3BF277")
    ("defghijklmnopqrstuvwxyz{|}" #uuid "2CBF26D6-D0B0-5CE8-8CB8-D2E41C45A693")
    ("efghijklmnopqrstuvwxyz{|}~" #uuid "5A43F434-1389-567B-8CF2-C6DC2CE12584")
    ("fghijklmnopqrstuvwxyz{|}~ " #uuid "53014805-B9A6-5AEE-BCE7-8B625B191B21")
    ("ghijklmnopqrstuvwxyz{|}~ !" #uuid "671E3E93-1193-527E-9036-BE959A40D657")
    ("hijklmnopqrstuvwxyz{|}~ !\"" #uuid "F89DAAC8-2905-5ED9-A4B1-A32FD588C9CC")
    ("ijklmnopqrstuvwxyz{|}~ !\"#" #uuid "0F0BE36D-B240-5915-8A63-826AE4CC1C60")
    ("jklmnopqrstuvwxyz{|}~ !\"#$" #uuid "F8C0C1FE-D6D3-531E-B822-DD94B0F3B3D5")
    ("klmnopqrstuvwxyz{|}~ !\"#$%" #uuid "24581D7C-0AE4-5BE4-9013-71CBC582E25D")
    ("lmnopqrstuvwxyz{|}~ !\"#$%&" #uuid "DBE82A12-620C-548C-8D24-C432995DD0D6")
    ("mnopqrstuvwxyz{|}~ !\"#$%&'" #uuid "83480C50-F2EE-5CF1-943E-2C5C4753D39F")
    ("nopqrstuvwxyz{|}~ !\"#$%&'(" #uuid "61E3F2AE-7EA0-5D75-90BF-C3FB14C5B764")
    ("opqrstuvwxyz{|}~ !\"#$%&'()" #uuid "95E8012A-8401-5AEE-8C55-C038233AA12D")
    ("pqrstuvwxyz{|}~ !\"#$%&'()*" #uuid "0A76BB26-CB8F-5B74-A25C-7B14AA1231E3")
    ("qrstuvwxyz{|}~ !\"#$%&'()*+" #uuid "D7C6DBD5-F976-5535-B447-E9887E9305B4")
    ("rstuvwxyz{|}~ !\"#$%&'()*+," #uuid "CFF90D62-C9AE-5D27-B71D-C26937F45B86")
    ("stuvwxyz{|}~ !\"#$%&'()*+,-" #uuid "FA856CEE-2A0A-5FEF-A58E-E19998333A73")
    ("tuvwxyz{|}~ !\"#$%&'()*+,-." #uuid "EA8CC78B-3FED-5926-94CB-C07978EE18DD")
    ("uvwxyz{|}~ !\"#$%&'()*+,-./" #uuid "8DD82AD7-11EC-520E-A620-42D7F71CDAE2")
    ("vwxyz{|}~ !\"#$%&'()*+,-./0" #uuid "5835D993-AA8B-57A5-8128-84C57D100CB5")
    ("wxyz{|}~ !\"#$%&'()*+,-./01" #uuid "B66C29F2-F34A-519F-B459-3EBF5301679F")
    ("xyz{|}~ !\"#$%&'()*+,-./012" #uuid "9573FC13-FAAD-5BBE-8F3F-801A9B713DB2")
    ("yz{|}~ !\"#$%&'()*+,-./0123" #uuid "5190E6CA-05CD-5970-86FA-9571C2CBC21D")
    ("z{|}~ !\"#$%&'()*+,-./01234" #uuid "75A67394-441A-5BC5-AEC9-ADD6DC77DDBA")
    ("{|}~ !\"#$%&'()*+,-./012345" #uuid "A2F570E1-A297-5F7E-BD15-AC9C76C8B07D")
    ("|}~ !\"#$%&'()*+,-./0123456" #uuid "46610A2C-7628-5F1B-8C5A-155639D29120")
    ("}~ !\"#$%&'()*+,-./01234567" #uuid "2127C846-ACF4-5418-8454-F8B7D309E338")
    ("~ !\"#$%&'()*+,-./012345678" #uuid "9D321B3F-E7E8-53A3-9EA5-466DF23617A9")
    ))


(deftest check-v5-oid-ns-cases
  (testing "v5 oid-ns case-based correctness..."
    (doseq [case +v5-oid-ns-cases+]
      (is (= (second case) (v5 +namespace-oid+ (first case)))))))
