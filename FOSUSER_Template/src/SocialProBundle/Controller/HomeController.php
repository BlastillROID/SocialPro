<?php
/**
 * Created by PhpStorm.
 * User: salah
 * Date: 3/13/2017
 * Time: 2:09 AM
 */

namespace SocialProBundle\Controller;


use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class HomeController extends Controller{

    public function adminPageAction(){
        return $this->render('SocialProBundle:Dashboard:dashboard.html.twig');
    }

}