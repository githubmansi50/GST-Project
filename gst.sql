-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2018 at 09:01 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gst`
--

-- --------------------------------------------------------

--
-- Table structure for table `acts`
--

CREATE TABLE `acts` (
  `id` int(15) NOT NULL,
  `caption` varchar(100) NOT NULL,
  `heading` varchar(100) NOT NULL,
  `act` varchar(5000) NOT NULL,
  `section` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acts`
--

INSERT INTO `acts` (`id`, `caption`, `heading`, `act`, `section`) VALUES
(1, 'none', 'All registered product under a registered brand should include GST in its cost', 'GST act on goods', '101');

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(15) NOT NULL,
  `caption` varchar(100) NOT NULL,
  `heading` varchar(50) NOT NULL,
  `body` text NOT NULL,
  `startdate` varchar(15) NOT NULL,
  `enddate` varchar(15) NOT NULL,
  `image` varchar(20) NOT NULL DEFAULT 'no image'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `caption`, `heading`, `body`, `startdate`, `enddate`, `image`) VALUES
(1, 'India expert', 'Do not rush to alter set GST deadlines', 'Tax officials will reportedly start the process of matching returns and invoices under the goods and services tax (GST), after it had been announced that invoice-matching would be deferred till March. Chop and change should be kept to the minimum in policy and administration, to make compliance easy for businesses. Apparently, the government wants to check the veracity of the input tax credit claims due to worries over slowing revenues.\r\n\r\nGST allows producers to claim credit for the taxes paid on inputs, and, to the extent credit is used, government revenue dips. Rightly, the GST law mandates matching of the details of inward supply with the corresponding details of outward supply to curb evasion.\r\n\r\nThe need is to have a non-obtrusive and administratively efficient system of matching returns and invoices. One way would be to mandate large buyers, with a great deal to lose from any false claims that get discovered later, of inputs from small suppliers to deduct GST at source on their purchases. They can claim input tax credit, while depositing the tax collected on their onward sales.\r\n\r\nBoth the buyer and the supplier will file returns showing the tax incidence and collection, resulting in one set of invoices getting matched. This would ease the pain for small suppliers, and improve the flow of their working capital: they would no longer have to borrow to pay tax. They have to pay tax immediately after they sell their goods but get paid after months. The interest on debt incurred to pay tax hurts these businesses, with little access to formal finance.\r\n\r\nThe bonus to pay tax after deducting the right amount of input tax credit would shift to large buyers, while the small supplier will only have to file returns showing the value of its supply and the tax that the large buyer has deducted.\r\n\r\nSource :  The Economic Times', '05/01/2018', '10/01/2018', '330-gst-article.jpg'),
(2, 'India Express', 'Your cheat sheet to ease GST compliance', 'In the past couple of months, there has been enough discussion and debate around GST and its implementation. We have almost seen it all, whether it is talking about the viability of GST, its impact on economy, global comparison with various nations which have adopted the structure, experts from various sectors dissecting it, etc. The fact which is now a reality of the day leaves no option for the businesses and other sectors but to adopt and accept this reform in the Indian tax structure. And, in all fairness, this change is only going to lead to a larger national good in the long run.\n\nIf we talk about introduction of GST, like any other major transformation in the country- this fact will also take some time to settle in the minds of the people and for the businesses to change the way of doing businesses. The question remains that why is there such a furore and pother about something which has been scanned by various financial experts and has been implemented after thorough economic deliberation for larger good of the nation? Maybe its just the fear of unknown.\n\nIn Indias tax compliance structure, major challenge lies in the fact that India is not a DIY (Do it yourself) economy. Whenever there is anything related to direct/ indirect business taxation we prefer and reach out to our very own subject matter expert. The CA friend to do it for us. Despite the availability of various tools and techniques which can actually help us ease the compliance, we ignore them being too complex for our understanding. Taxation is not the only subject.\n\n\n(The author of this article is Ankit Agarwal, MD at Alankit, GST Suvidha Provider)', '05/01/2018', '10/01/2018', '329-gst-article.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `bookmarks`
--

CREATE TABLE `bookmarks` (
  `id` int(15) NOT NULL,
  `type` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `bid` varchar(15) NOT NULL,
  `uid` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookmarks`
--

INSERT INTO `bookmarks` (`id`, `type`, `name`, `bid`, `uid`) VALUES
(1, '1', 'GST raid on jewellers: Government begins crackdown', '2', '3'),
(3, '1', 'Central Tax Rate Notification', '1', '3'),
(4, '3', 'Do not rush to alter set GST deadlines', '1', '3');

-- --------------------------------------------------------

--
-- Table structure for table `calculatordata`
--

CREATE TABLE `calculatordata` (
  `id` int(15) NOT NULL,
  `field` varchar(30) NOT NULL,
  `productcategory` varchar(30) NOT NULL,
  `cgst_percentage` varchar(20) NOT NULL,
  `sgst_percentage` varchar(20) NOT NULL,
  `cgst_sgst_percent` varchar(20) NOT NULL,
  `minimum_price` varchar(20) NOT NULL,
  `max_price` varchar(20) NOT NULL,
  `min_qauntity` varchar(20) NOT NULL,
  `max_qauntity` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calculatordata`
--

INSERT INTO `calculatordata` (`id`, `field`, `productcategory`, `cgst_percentage`, `sgst_percentage`, `cgst_sgst_percent`, `minimum_price`, `max_price`, `min_qauntity`, `max_qauntity`) VALUES
(1, 'Advertising', 'Ad', '18', '18', '36', '10000', '50000', '1', '5');

-- --------------------------------------------------------

--
-- Table structure for table `discussion`
--

CREATE TABLE `discussion` (
  `id` int(15) NOT NULL,
  `post` varchar(1000) NOT NULL,
  `uid` varchar(15) NOT NULL,
  `uname` varchar(15) NOT NULL,
  `daten` varchar(15) NOT NULL,
  `timen` varchar(15) NOT NULL,
  `combinetime` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discussion`
--

INSERT INTO `discussion` (`id`, `post`, `uid`, `uname`, `daten`, `timen`, `combinetime`) VALUES
(1, 'What is GST?', '3', 'new', '08/01/2018', '19:00', '8/01/2018 19:00'),
(2, 'fhnz', '3', 'new', '08/01/2018', '19:16', '08/01/2018 19:16'),
(3, 'dgdhx', '3', 'new', '08/01/2018', '19:16', '08/01/2018 19:16'),
(4, 'dhdh', '3', 'new', '08/01/2018', '19:16', '08/01/2018 19:16'),
(5, 'types of gst?', '3', 'new', '09/01/2018', '10:29', '09/01/2018 10:29'),
(6, 'what is sgst?', '3', 'new', '09/01/2018', '10:30', '09/01/2018 10:30'),
(7, 'sending mag', '3', 'new', '09/01/2018', '10:37', '09/01/2018 10:37'),
(8, 'new msg', '3', 'new', '09/01/2018', '10:38', '09/01/2018 10:38'),
(9, 'my msg', '3', 'new', '09/01/2018', '10:39', '09/01/2018 10:39'),
(10, 'hello', '3', 'new', '09/01/2018', '10:47', '09/01/2018 10:47'),
(11, 'hello', '3', 'new', '09/01/2018', '10:49', '09/01/2018 10:49'),
(12, 'hello', '3', 'new', '09/01/2018', '10:49', '09/01/2018 10:49'),
(13, 'hi', '3', 'new', '09/01/2018', '11:02', '09/01/2018 11:02'),
(14, 'gst', '3', 'new', '09/01/2018', '11:05', '09/01/2018 11:05'),
(15, 'hst', '3', 'new', '09/01/2018', '11:09', '09/01/2018 11:09'),
(16, 'gst', '3', 'new', '09/01/2018', '11:10', '09/01/2018 11:10'),
(17, 'mst', '3', 'new', '09/01/2018', '11:15', '09/01/2018 11:15'),
(18, 'gegd', '3', 'new', '17/02/2018', '12:46', '17/02/2018 12:46');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `help_services`
--

CREATE TABLE `help_services` (
  `id` int(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `body` varchar(500) NOT NULL,
  `type` varchar(10) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `cost` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `help_services`
--

INSERT INTO `help_services` (`id`, `name`, `body`, `type`, `contact`, `cost`) VALUES
(1, 'Tax calculating helper', 'Your accounts will be managed by expert and tax will be calculated and optimised.', 'Service', 'Mr. Pradeep 9821786097', 'As per requirement');

-- --------------------------------------------------------

--
-- Table structure for table `imp_link`
--

CREATE TABLE `imp_link` (
  `id` int(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `caption` varchar(20) NOT NULL DEFAULT 'none',
  `body` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imp_link`
--

INSERT INTO `imp_link` (`id`, `name`, `caption`, `body`) VALUES
(1, 'Google link', 'none', 'www.google.com');

-- --------------------------------------------------------

--
-- Table structure for table `knowledge_solution`
--

CREATE TABLE `knowledge_solution` (
  `id` int(15) NOT NULL,
  `caption` varchar(100) NOT NULL DEFAULT 'none',
  `question` varchar(100) NOT NULL,
  `answer` varchar(1000) NOT NULL,
  `daten` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `knowledge_solution`
--

INSERT INTO `knowledge_solution` (`id`, `caption`, `question`, `answer`, `daten`) VALUES
(1, 'none', 'What is GST?', 'Goods and Services Tax (GST) is an indirect tax levied in India on the sale of goods and services. Goods and services are divided into five tax slabs for collection of tax - 0%, 5%, 12%, 18% and 28%. Petroleum products and Alcoholic drinks are taxed separately by the individual state governments. There is a special rate of 0.25% on rough precious and semi-precious stones and 3% on gold. In addition a cess of 22% or other rates on top of 28% GST applies on few items like aerated drinks, luxury cars and tobacco products.', '09/01/2018');

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `id` int(12) NOT NULL,
  `title` varchar(33) NOT NULL,
  `type` varchar(33) NOT NULL,
  `attachment` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `title`, `type`, `attachment`) VALUES
(1, 'chapter 1', '3', 'mechanicschapter1.mp4'),
(2, 'chapter 2', '1', 'texttest.txt');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` int(15) NOT NULL,
  `heading` varchar(50) NOT NULL,
  `body` varchar(5000) NOT NULL,
  `source` varchar(50) NOT NULL,
  `daten` varchar(15) NOT NULL,
  `image` varchar(20) NOT NULL DEFAULT 'no image'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `heading`, `body`, `source`, `daten`, `image`) VALUES
(1, 'Central Tax Rate Notification', 'Most of the goods are kept at the same rates as announced by the GST council earlier but rough or non industrial unworked diamond or precious stones will be charged CGST at the rate of 0.125%.\r\nList of goods exempt from CGST. No change in the list.\r\nOil, gas, coal and petroleum licenses and sub-contract licenses and leases will be charged GST at the rate of 2.5%.\r\nThe person liable to deduct TDS as per the GST law supplying intrastate goods or services to an unregistered person would be exempt from CGST.\r\nCashew nuts, not shelled or peeled, Bidi wrapper leaves (tendu), Tobacco leaves, silk yarn, Supply of lottery would have reverse charge applicable under GST.\r\nRefund of the unutilized ITC would not be provided in the case of the tax on output being lower than the tax on inputs for certain goods mainly related to the textile and railways.\r\nThe supply of goods by CSD to unit run canteens and authorized customers and supply of goods by the unit run canteens to the authorized customers.\r\n50% of the tax paid on inward supplies of goods by the CSD for further supply to unit run canteens or authorized customers can be claimed as refund under GST.\r\nPerson liable to deduct TDS as per the GST law supplying intra goods or services to an unregistered person would be exempt from CGST.\r\nIntrastate supply of second hand goods by a registered person who deals in selling second hand goods to an unregistered person would be exempt from CGST.', 'www.cleartax.in', '06/01/2017', 'no image'),
(2, 'GST raid on jewellers: Government begins crackdown', 'NEW DELHI: Suspecting large-scale evasion, the government has got into action as collection of Goods and Services Tax is declining. In the first case of its kind in Mumbai, and probably the biggest in the country, 90 parcels containing jewellery have been confiscated by the GST department for flouting of tax norms. 85 carriers have been detained for ferrying consignment without proper paperwork. \r\n\r\nIn November last year, the Kerala state GST Department had seized 14.5 kg of gold ornaments worth Rs 4.80 crore. In December, the department seized 3 kg of gold from a Mumbai native in Kochi. \r\n\r\nSo far, the government had been trying to lighten the compliance burden on businesses due to the complexity of the new tax regime. But the GST raids indicate that the government is no longer going to be soft on compliance. \r\n\r\nThis is the first such raid carried out by the department in Mumbai ever since the implementation of the GST by the government. \r\n\r\nNationwide launch of the eway bill from February 1 is expected to check evasion by ensuring goods are tagged and tax paid. It could not be rolled out earlier as software and nationwide system was not ready. \r\n\r\nThe government fears lack of monitoring of cargo movement could have allowed cash dealing across the entire value chain to evade taxes, which could have been reason for the sharp decline in GST collections in past two months. \r\n\r\nAccording to government data, GST collection for the month of November slipped to a five-month low of Rs 80,808 crore. Nearly six lakh taxpayers availing the simple composition scheme under the GST have paid a tax of only about Rs 250 crore for July-August, leading the government to suspect large-scale evasion. \r\n\r\nThe GST Council has decided to start eway bill for inter-state movement of goods from February 1. Eway bill is an electronic document that is required if goods worth more than Rs 50,000 is transported and carries the details of supplier, buyers and goods being transported. There have been fears the bill will lead to harassment as any cargo would be stopped for checking.', 'www.economictimes.indiatimes.com', '06/01/2018', 'gold-gst-news.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE `states` (
  `id` int(12) NOT NULL,
  `state` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `states`
--

INSERT INTO `states` (`id`, `state`, `district`) VALUES
(1, 'Maharashtra', 'Mumbai'),
(2, 'Maharashtra', 'Pune'),
(3, 'Karnatak', 'Chennai'),
(4, 'Maharashtra', 'Nashik');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(12) NOT NULL,
  `state` varchar(100) NOT NULL,
  `district` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `rating` varchar(10) NOT NULL DEFAULT '0.0',
  `token` varchar(500) NOT NULL DEFAULT 'not generated'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `mobile`, `state`, `district`, `email`, `password`, `rating`, `token`) VALUES
(3, 'new', '7020907782', 'Maharashtra', 'Mumbai', 'neew@gmail.com', '123', '0.0', 'f6Dw_ph1Em8:APA91bHwC2jHupO8MjfJsC5DDMduvkd4JRePpKZS17bKCuyob2N3zbndCmIPjJ3qsMHu1T4E-H7tsM85oMJkQMR4ExPIk9gLoDl_d8I_NM6D5acaoAtmpmh45rntCg5ywuf5koj65IjV'),
(4, 'Diksha', '7378660111', 'Maharashtra', 'Mumbai', 'd@gmail.com', '123456', '0.0', 'efOafqn6JYY:APA91bHJoGKoZAxqxX5YdcNZP-ZPlF2dYIsbnsOUxtp72rejPR7rzf1PvTJMfShXHGHaL7oyO9zRFCpIAhTSCxrS_Z1_MbN4CKe06IpXkdZ4fbDeXPA_9u4Eq2fEb-fDuOAT_tpa1tYi'),
(5, 'diksha', '9480963966', 'Maharashtra', 'Mumbai', 'da@gmail.com', '12345', '0.0', 'not generated'),
(6, 'Diksha', '1234567890', 'Maharashtra', 'Mumbai', 'ma@gmail.com', '123', '0.0', 'fYJ6Q5IeTkw:APA91bGocFCyoFu418TQDCyeriGZJ6bJvFiaJdvb4psxIqka5BnQSXu920XsZWyx7_PyHjTi_zS69Xgs2vw26KR70uOwVeKvSAE2uQWsFIYdNksx7LyB2o-lcQbhy0h3kkup9sK5Gdud');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acts`
--
ALTER TABLE `acts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bookmarks`
--
ALTER TABLE `bookmarks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `calculatordata`
--
ALTER TABLE `calculatordata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `help_services`
--
ALTER TABLE `help_services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `imp_link`
--
ALTER TABLE `imp_link`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `knowledge_solution`
--
ALTER TABLE `knowledge_solution`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `states`
--
ALTER TABLE `states`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acts`
--
ALTER TABLE `acts`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `bookmarks`
--
ALTER TABLE `bookmarks`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `calculatordata`
--
ALTER TABLE `calculatordata`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `help_services`
--
ALTER TABLE `help_services`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `imp_link`
--
ALTER TABLE `imp_link`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `knowledge_solution`
--
ALTER TABLE `knowledge_solution`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `states`
--
ALTER TABLE `states`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
