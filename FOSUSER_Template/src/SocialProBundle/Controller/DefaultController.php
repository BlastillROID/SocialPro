<?php

namespace SocialProBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('SocialProBundle:Default:index.html.twig');
    }
}
